package me.walker.kafkademo.mapper;

import me.walker.kafkademo.model.UserInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserInfoMapper {
     UserInfo selectByPrimaryKey();
}
