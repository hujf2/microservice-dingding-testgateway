package com.baomidou.samples.druid.mybatis.mapper.sourcedata;

import com.baomidou.samples.druid.mybatis.service.message.TestBase;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * @author junfeng.hu
 * @create 2019-11-28 14:31
 */
public class DingdingMapperTest extends TestBase {

    @SuppressWarnings("all")
    @Autowired
    DingdingMapper dingdingMapper;

    @Test
    public void select() {
        System.out.println("dingdingMapper.select(\"aaa\") = " + dingdingMapper.select("aaa"));
    }

    @Test
    public void insert() {
        String sign  =  DigestUtils.md5DigestAsHex(String.valueOf(new Date().getTime()).getBytes());
        System.out.println("sign = " + sign);
        dingdingMapper.insert(
                sign,
                "v1",
                "重庆预测",
                "https://oapi.dingtalk.com/robot/send?access_token=c6cab3dba35fc0c62bf6a344845d48ea0131943f681ae0f876ad6ee4548c00db",
                "13436785923",
                1,
                30,
                1,
                "重庆市"
                );
    }
}