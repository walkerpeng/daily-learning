package me.walker.kafkademo.mapper;

import me.walker.kafkademo.model.RealTimeEstimateResult;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RealTimeEstimateMapper {
     void addRealTimeEstimateList(List<RealTimeEstimateResult> realTimeEstimateResultList);
}
