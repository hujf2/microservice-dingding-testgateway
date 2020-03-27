package com.baomidou.samples.druid.mybatis.service.message;


import com.alibaba.fastjson.JSON;
import com.baomidou.samples.druid.mybatis.entity.dingding.DingdingMsgModel;
import com.baomidou.samples.druid.mybatis.entity.dingding.DingdingMsgModel.AtBean;
import com.baomidou.samples.druid.mybatis.entity.dingding.DingdingMsgModel.TextBean;
import com.baomidou.samples.druid.mybatis.structrue.CallResult;
import com.mapabc.springboot.common.log.LogBeanBuilders;
import com.mapabc.springboot.common.log.LoggerLogcenterUtil;
import lombok.Data;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @author junfeng.hu
 * @create 2018-11-21 15:38
 */

@Service
public class HttpService {

    public void post(String webhook, String json){

        CloseableHttpClient httpclient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(webhook);
        try {
            StringEntity s = new StringEntity(json, HTTP.UTF_8);
            s.setContentType("application/json");//发送json数据需要设置contentType
            post.setEntity(s);
            HttpResponse res = httpclient.execute(post);
            if(res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                String result = EntityUtils.toString(res.getEntity());// 返回json格式：

                LoggerLogcenterUtil.info(getClass(), new LogBeanBuilders()
                        .setAccessLog("钉钉服务端返回: {0}", result)
                        .setMethodName("doSend"));
            }
        } catch (Exception e) {
            //throw new RuntimeException(e);
            LoggerLogcenterUtil.info(getClass(), new LogBeanBuilders()
                    .setAccessLog("钉钉服务端错误: {0}", e.getMessage())
                    .setMethodName("doSend").setPrintStack(e));
        }

    }



}
