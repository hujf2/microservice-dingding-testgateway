package com.baomidou.samples.druid.mybatis.mapper.sourcedata;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.samples.druid.mybatis.entity.dingding.TDingDing;
import com.baomidou.samples.druid.mybatis.entity.dingding.TDingDingAlert;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface DingdingAlertMapper {
  @DS("slave_2")
  @Select("select pk_id,sign,failure_type,failure_time,note,app_name,artifact_id, unix_timestamp(create_time) as create_time, update_time from t_dingding_alert where sign=#{sign}  order by pk_id desc limit 1 ")
  TDingDingAlert select(@Param("sign") String sign);


  @DS("slave_2")
  @Insert(
          "INSERT INTO t_dingding_alert(" +
                  "sign,"+
                  "failure_type,"+
                  "failure_time,"+
                  "note,"+
                  "app_name,"+
                  "artifact_id"+
                  ") "+
          "VALUES(" +
                  "#{sign},"+
                  "#{failure_type},"+
                  "#{failure_time},"+
                  "#{note},"+
                  "#{app_name},"+
                  "#{artifact_id}"+
                  ")"
  )
  boolean insert(
          @Param("sign") String sign,
          @Param("failure_type") Integer failure_type,
          @Param("failure_time") Long failure_time,
          @Param("note") String note,
          @Param("app_name") String app_name,
          @Param("artifact_id") String artifact_id
  );
}


