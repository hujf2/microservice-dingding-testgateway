package com.baomidou.samples.druid.mybatis.mapper.sourcedata;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.samples.druid.mybatis.entity.User;
import com.baomidou.samples.druid.mybatis.entity.dingding.TDingDing;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface DingdingMapper {
  @DS("slave_2")
  @Select("select sign,api_version,project_name,city,webhook,at_mobile,enabled,intervals,is_del from t_dingding where is_del=1 and sign=#{sign} limit 1 ")
  TDingDing select(@Param("sign") String sign);

  @DS("slave_2")
  @Insert(
          "INSERT INTO t_dingding(" +
                  "sign,"+
                  "api_version,"+
                  "project_name,"+
                  "webhook,"+
                  "at_mobile,"+
                  "enabled,"+
                  "intervals,"+
                  "is_del,"+
                  "city"+
                  ") "+
          "VALUES(" +
                  "#{sign},"+
                  "#{api_version},"+
                  "#{project_name},"+
                  "#{webhook},"+
                  "#{at_mobile},"+
                  "#{enabled},"+
                  "#{intervals},"+
                  "#{is_del},"+
                  "#{city}"+
                  ")"
  )
  boolean insert(
          @Param("sign") String   sign,
          @Param("api_version") String   api_version,
          @Param("project_name") String   project_name,
          @Param("webhook") String   webhook,
          @Param("at_mobile") String   at_mobile,
          @Param("enabled") Integer   enabled,
          @Param("intervals") Integer   intervals,
          @Param("is_del") Integer   is_del,
          @Param("city") String   city
  );
}


