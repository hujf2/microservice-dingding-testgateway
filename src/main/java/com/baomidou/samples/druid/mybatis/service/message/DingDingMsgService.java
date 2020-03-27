package com.baomidou.samples.druid.mybatis.service.message;


import com.alibaba.fastjson.JSON;
import com.baomidou.samples.druid.mybatis.appconst.ENUMFAILURETYPE;
import com.baomidou.samples.druid.mybatis.entity.dingding.DingdingAlertModel;
import com.baomidou.samples.druid.mybatis.entity.dingding.DingdingMsgModel;
import com.baomidou.samples.druid.mybatis.entity.dingding.TDingDing;
import com.baomidou.samples.druid.mybatis.entity.dingding.TDingDingAlert;
import com.baomidou.samples.druid.mybatis.mapper.sourcedata.DingdingAlertMapper;
import com.baomidou.samples.druid.mybatis.mapper.sourcedata.DingdingMapper;
import com.baomidou.samples.druid.mybatis.structrue.CallResult;
import com.mapabc.springboot.common.StringUtils;
import com.mapabc.springboot.common.log.LogBeanBuilders;
import com.mapabc.springboot.common.log.LoggerLogcenterUtil;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.baomidou.samples.druid.mybatis.entity.dingding.DingdingMsgModel.TextBean;
import com.baomidou.samples.druid.mybatis.entity.dingding.DingdingMsgModel.AtBean;
/**
 * @author junfeng.hu
 * @create 2018-11-21 15:38
 */

@Data
@Service
public class DingDingMsgService {

    @Autowired
    HttpService httpService;

    private String webhook;
    private AtBean at ;
    private String context;

    @SuppressWarnings("all")
    @Autowired
    DingdingMapper dingdingMapper;

    @SuppressWarnings("all")
    @Autowired
    DingdingAlertMapper dingdingAlertMapper;


    public void doThat(Object object){
        TextBean text = new TextBean();
        text.setContent(context);
//        AtBean at = new AtBean();
        //转还需要at的责任人
        DingdingMsgModel dingdingMsgModel = new DingdingMsgModel();
        //表示最少有一个手机号
        if(at!=null) {
            dingdingMsgModel.setAt(at);
        }
        dingdingMsgModel.setMsgtype("text");
        dingdingMsgModel.setText(text);
        System.out.println("发送消息 : " + JSON.toJSONString(dingdingMsgModel));
        httpService.post(webhook,JSON.toJSONString(dingdingMsgModel));
    }




    public CallResult<Object> postMessageSimple(String webhook, String json){
        httpService.post(webhook,json);
        return CallResult.success();
    }






    public  CallResult foo(DingdingAlertModel dingdingAlertModel){
        TDingDing tDingDing = dingdingMapper.select(dingdingAlertModel.getSign());
        if(tDingDing == null){
            return  CallResult.failure("sign 非法调用");
        }

        if(dingdingAlertModel.getNote().size()<1){
            return  CallResult.failure("note 描述不可以为空");
        }
        //enable=0 表禁止推送
        if(tDingDing.getEnabled() == 0){
            LoggerLogcenterUtil.info(getClass(),new LogBeanBuilders()
                    .setAccessLog("禁止推送已经设置:{0}", JSON.toJSONString(dingdingAlertModel)));
            return  CallResult.failure(StringUtils.join("禁止推送",JSON.toJSONString(dingdingAlertModel)));
        }

        //规定的时间周期其禁止重复发送钉钉警报
        TDingDingAlert tDingDingAlert = dingdingAlertMapper.select(dingdingAlertModel.getSign());
        long dateTime = new Date().getTime()/1000;
        System.out.println("dateTime = " + dateTime);
        //tDingDingAlert!=null 说明第一次推送钉钉
        if (tDingDingAlert != null && ((dateTime - tDingDing.getIntervals()) < tDingDingAlert.getCreate_time())) {
            return  CallResult.failure(StringUtils.join("禁止推送,在规定的时间区间内禁止重复推送",JSON.toJSONString(dingdingAlertModel)));
        }
        setWebhook(tDingDing.getWebhook());

        ArrayList<String> mobilesList = new ArrayList<>();
        atMobiles(tDingDing.getAt_mobile(),mobilesList);
        atMobiles(dingdingAlertModel.getAtMobile(),mobilesList);
        //有需要at的责任人
        if(mobilesList.size()>0){
            at = new AtBean();
            at.setAtMobiles(mobilesList);
        }

//      项目概要:烟台-开发区交警二期项目
//      故障类型:连通性检测失败
//      应用名称:高德事件回流
//      接口名称:10.19.10.10:222/push-service
//      协议:http
//      错误信息:500 Internal Server Error
//      故障可能在位置:数据库连接问题

        ArrayList<String> array = new ArrayList<>();
        array.add(StringUtils.join("城市名称",":",tDingDing.getCity()));
        array.add(StringUtils.join("项目名称",":",tDingDing.getProject_name()));
        array.add(StringUtils.join("应用名称",":",dingdingAlertModel.getAppName()));
        array.add(StringUtils.join("故障类型",":", ENUMFAILURETYPE.getName(dingdingAlertModel.getFailureType())));
        //转换出故障时间
        SimpleDateFormat format0 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = format0.format(Long.valueOf(dingdingAlertModel.getFailureTime()) * 1000);
        array.add(StringUtils.join("故障时间",":",time));
        array.add("=====详情=====");
        array.add(StringUtils.join("模块名称",":",dingdingAlertModel.getArtifactId()));

        //将开发人员的详细描述信息拼接好
        dingdingAlertModel.getNote().forEach(item->{
            array.add(StringUtils.join(item));
        });
        //格式化消息体
        StringBuffer msg = new StringBuffer();
        array.forEach(item->{
            msg.append(StringUtils.join(item,"\n"));
        });

        System.out.println("StringUtils.join(msg) = " + StringUtils.join(msg));
        //将所有信息组织好了

        setContext(StringUtils.replace(StringUtils.join(msg),":",": "));
        System.out.println("getContext() = " + getContext());
        //发送钉钉
        doThat(null);
        
        //在消息表中增加一条警报
        dingdingAlertMapper.insert(
                dingdingAlertModel.getSign(),
                dingdingAlertModel.getFailureType(),
                Long.valueOf(dingdingAlertModel.getFailureTime()),
                context,
                dingdingAlertModel.getAppName(),
                dingdingAlertModel.getArtifactId()
        );
        return  CallResult.success();
    }


    public void atMobiles(String atmobiles, ArrayList mobilesList){

        if(atmobiles==null) return;
        else if("".equals(atmobiles)) return;
        else if(atmobiles.length()>0){
            String[] m = atmobiles.split(",");
            for (int i = 0; i < m.length; i++) {
                mobilesList.add(m[i]);
            }
            return;
        }else {
            System.out.println(" 这个情况没有考虑到 " );
            return;
        }
    }

}


