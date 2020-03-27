package com.baomidou.samples.druid.mybatis.service.message;

import com.baomidou.samples.druid.mybatis.DingDingProxyServerApplication;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Import(DingDingProxyServerApplication.class)
public class TestBase {

}