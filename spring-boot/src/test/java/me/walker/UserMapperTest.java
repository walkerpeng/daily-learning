package me.walker;

import me.walker.kafkademo.mapper.UserInfoMapper;
import me.walker.kafkademo.model.UserInfo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserMapperTest extends ApplicationTests{
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Test
    public void testQuery() {
        UserInfo user = userInfoMapper.selectByPrimaryKey();
        System.out.println(user);
    }
}
