package com.baomidou.samples.druid.mybatis.controller;

import com.baomidou.samples.druid.mybatis.entity.dingding.DingdingAlertModel;
import com.baomidou.samples.druid.mybatis.entity.dingding.ZentaoMessageEntity;
import com.baomidou.samples.druid.mybatis.service.message.DingDingMsgService;
import com.baomidou.samples.druid.mybatis.structrue.CallResult;
import com.mapabc.springboot.common.log.LogBeanBuilders;
import com.mapabc.springboot.common.log.LoggerLogcenterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author liuzh
 * @since 2015-12-19 11:10
 */
@RestController
public class DemoController {

    @RequestMapping(value="/api/hi", method={RequestMethod.GET})
    public CallResult getController(){

        return CallResult.success();
    }






}






