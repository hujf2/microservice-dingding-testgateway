package com.baomidou.samples.druid.mybatis.appconst;

import com.mapabc.springboot.common.log.LogBeanBuilders;
import com.mapabc.springboot.common.log.LoggerLogcenterUtil;

/**
 * @author junfeng.hu
 * @create 2019-11-16 0:40
 */
public class Utils {
    public static String getTypeFromList(String TURNTYPE){
        if(TURNTYPE.contains(",")){
            TURNTYPE = TURNTYPE.split(",")[0];
            return TURNTYPE;
        }
        return TURNTYPE;
    }
}
