package com.baomidou.samples.druid.mybatis;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.ComponentScan;
//测试一下cicd 2
@EnableZuulProxy
@EnableDiscoveryClient
@ComponentScan("com.baomidou.samples.druid.mybatis.*")
@SpringBootApplication(exclude = DruidDataSourceAutoConfigure.class)
@MapperScan("com.baomidou.samples.druid.mybatis.mapper")
public class DingDingProxyServerApplication {
  public static void main(String[] args) {
    SpringApplication.run(DingDingProxyServerApplication.class, args);
  }
}