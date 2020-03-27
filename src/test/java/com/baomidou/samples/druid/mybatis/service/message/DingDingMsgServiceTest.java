package com.baomidou.samples.druid.mybatis.service.message;

import com.mapabc.springboot.common.StringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * @author junfeng.hu
 * @create 2019-11-27 17:10
 */
public class DingDingMsgServiceTest extends TestBase{

    @Autowired
    DingDingMsgService dingDingMsgService;

    @Test
    public void doThat() {
        //dingDingMsgService.setAtmobiles("13910056241");
        //dingDingMsgService.setWebhook("https://oapi.dingtalk.com/robot/send?access_token=c6cab3dba35fc0c62bf6a344845d48ea0131943f681ae0f876ad6ee4548c00db" );
        //dingDingMsgService.doThat(null);
        String x = StringUtils.replace("key:value,key:value,",":",": ");
        System.out.println("x = " + x);
    }

}


