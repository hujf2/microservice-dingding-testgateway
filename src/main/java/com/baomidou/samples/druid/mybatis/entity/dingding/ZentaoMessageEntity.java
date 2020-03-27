package com.baomidou.samples.druid.mybatis.entity.dingding;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ZentaoMessageEntity {
    private String webhook;
    private String json;
}