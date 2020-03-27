package com.baomidou.samples.druid.mybatis.mapper.sourcedata;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.samples.druid.mybatis.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CrossMapper {
  @DS("slave_1")
//  @Select("select inter_id,inter_name,lng,lat,geohash,is_signlight,inter_flowtype_no,inter_type_no,entrance_cnt,is_corner,areacode,sub_date,data_from,adcode,data_version,mesh_guf,node,node_id,newnodetype,newnodetype_guf,wkt,forktype,form_way,ad_bnd,ct_cross,source,min_cross,st_astext(geom) as geom  from \"cross\" where inter_id='14UO30BFEB0'")
  @Select("select inter_id,inter_name,lng,lat,geohash,is_signlight,inter_flowtype_no,inter_type_no,entrance_cnt,is_corner,areacode,sub_date,data_from,adcode,data_version,mesh_guf,node,node_id,newnodetype,newnodetype_guf,wkt,forktype,form_way,ad_bnd,ct_cross,source,min_cross,st_astext(geom) as geom  from \"cross\" ")
  List<User> select(@Param("RID") String RID);

}


