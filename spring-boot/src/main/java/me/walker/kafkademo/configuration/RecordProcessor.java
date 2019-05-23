package me.walker.kafkademo.configuration;

import lombok.extern.slf4j.Slf4j;
import me.walker.kafkademo.mapper.RealTimeEstimateMapper;
import me.walker.kafkademo.model.RealTimeEstimateResult;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;
import org.joda.time.Duration;
import org.joda.time.LocalDateTime;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class RecordProcessor implements Runnable{

    //保存MsgReceiver线程发送过来的消息
    private BlockingQueue<ConsumerRecord<String, String>> queue = new LinkedBlockingQueue<ConsumerRecord<String, String>>();
    //用于向consumer线程提交消费偏移的队列
    private BlockingQueue<Map<TopicPartition, OffsetAndMetadata>> commitQueue;
    //上一次提交时间
    private LocalDateTime lastTime = LocalDateTime.now();
    //消费了20条数据, 就进行一次提交
    private long commitLength = 20L;
    //距离上一次提交多久, 就提交一次
    private Duration commitTime = Duration.standardSeconds(2);
    //当前该线程消费的数据条数
    private int completeTask = 0;
    //保存上一条消费的数据
    private ConsumerRecord<String, String> lastUncommittedRecord;

    private RealTimeEstimateMapper realTimeEstimateMapper;

    private List<RealTimeEstimateResult> resultList = new ArrayList<RealTimeEstimateResult>();

    public RecordProcessor() {
    }

    //用于保存消费偏移量的queue, 由MsgReceiver提供
    public RecordProcessor(BlockingQueue<Map<TopicPartition, OffsetAndMetadata>> commitQueue, RealTimeEstimateMapper realTimeEstimateMapper) {
        this.commitQueue = commitQueue;
        this.realTimeEstimateMapper = realTimeEstimateMapper;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                //有时间限制的poll, consumer发送消费过来的队列. 每个处理线程都有自己的队列.
                ConsumerRecord<String, String> record = queue.poll(100, TimeUnit.MICROSECONDS);
                if (record != null) {
                    //处理过程
                    process(record);
                    //完成任务数加1
                    this.completeTask++;
                    //保存上一条处理记录
                    lastUncommittedRecord = record;
                }
                //提交偏移给consumer
                commitToQueue();
            }
        } catch (InterruptedException e) {
            //线程被interrupt,直接退出
            log.info(Thread.currentThread() + "is interrupted");
        }
    }

    private void process(ConsumerRecord<String, String> record) {
        log.info(record.value());
        //进行自己的业务处理
        RealTimeEstimateResult realTimeEstimateResult = new RealTimeEstimateResult();
        resultList.add(realTimeEstimateResult);
        //限流，每次插入500条数据
        if (resultList.size() == 500) {
            realTimeEstimateMapper.addRealTimeEstimateList(resultList);
            resultList.clear();
        }

    }

    //将当前的消费偏移量放到queue中, 由MsgReceiver进行提交
    private void commitToQueue() throws InterruptedException {
        //如果没有消费或者最后一条消费数据已经提交偏移信息, 则不提交偏移信息
        if (lastUncommittedRecord == null) {
            return;
        }
        //如果消费了设定的条数, 比如又消费了commitLength消息
        boolean arrivedCommitLength = this.completeTask % commitLength == 0;
        //获取当前时间, 看是否已经到了需要提交的时间
        LocalDateTime currentTime = LocalDateTime.now();
        boolean arrivedTime = currentTime.isAfter(lastTime.plus(commitTime));
        //如果消费了设定条数, 或者到了设定时间, 那么就发送偏移到消费者, 由消费者非阻塞poll这个偏移信息队列, 进行提交
        if (arrivedCommitLength || arrivedTime) {
            lastTime = currentTime;
            long offset = lastUncommittedRecord.offset();
            int partition = lastUncommittedRecord.partition();
            String topic = lastUncommittedRecord.topic();
            TopicPartition topicPartition = new TopicPartition(topic, partition);
            log.debug("partition: " + topicPartition + " submit offset: " + (offset + 1L) + " to consumer task");
            Map<TopicPartition, OffsetAndMetadata> map = Collections.singletonMap(topicPartition, new OffsetAndMetadata(offset + 1L));
            commitQueue.put(map);
            //置空
            lastUncommittedRecord = null;
        }
    }

    //consumer线程向处理线程的队列中添加record
    public void addRecordToQueue(ConsumerRecord<String, String> record) {
        try {
            queue.put(record);
        } catch (InterruptedException e) {
            log.error(e.getMessage(), e);
        }
    }
}
