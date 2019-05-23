package me.walker.kafkademo.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PropsConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String broker;

    @Value("${spring.kafka.consumer.group-id}")
    private String groupId;

    @Value("${spring.kafka.consumer.enable-auto-commit}")
    private String enableAutoCommit;

    @Value("${spring.kafka.template.default-topic}")
    private String topic;

    public String getBroker() {
        return broker;
    }

    public String getGroupId() {
        return groupId;
    }

    public String getEnableAutoCommit() {
        return enableAutoCommit;
    }

    public String getTopic() {
        return topic;
    }
}
