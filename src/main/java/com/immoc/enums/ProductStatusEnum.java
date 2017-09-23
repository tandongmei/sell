package com.immoc.enums;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by tandongmei on 2017/9/8.
 */
@Getter
public enum ProductStatusEnum implements CodeEnum{

    UP(0,"上架"),
    DOWN(1,"下架");

    private Integer code;

    private String msg;

    ProductStatusEnum(Integer code,String msg){
        this.code = code;
        this.msg = msg;
    }
}
