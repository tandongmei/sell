package com.immoc.common;

import lombok.Data;

/**
 * Created by tandongmei on 2017/9/8.
 */
@Data
public class RestfulResponse<T> {

    /** 状态码*/
    private Integer code = 0;

    /** 提示信息*/
    private String msg = "成功";

    /** 数据*/
    private T data;
}
