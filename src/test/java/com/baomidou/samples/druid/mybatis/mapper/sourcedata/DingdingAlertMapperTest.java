package com.baomidou.samples.druid.mybatis.mapper.sourcedata;

import com.baomidou.samples.druid.mybatis.service.message.TestBase;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * @author junfeng.hu
 * @create 2019-11-28 15:38
 */
public class DingdingAlertMapperTest extends TestBase {

    @SuppressWarnings("all")
    @Autowired
    DingdingAlertMapper dingdingAlertMapper;

    @Test
    public void select() {
        System.out.println("dingdingAlertMapper.select(\"aaa\") = " + dingdingAlertMapper.select("aaa"));


    }

    @Test
    public void insert() {
        dingdingAlertMapper.insert(
                "aaa",
                0,
                1574934255L,
                "这是一则钉钉推送消息",
                "高德事件回流服务",
                "microservice-event"
        );
    }
}