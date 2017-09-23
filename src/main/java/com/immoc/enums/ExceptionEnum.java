package com.immoc.enums;

import lombok.Getter;

/**
 * Created by Administrator on 2017/9/10 0010.
 */
@Getter
public enum ExceptionEnum {
    SERVER_ERROR(-99,"系统异常"),
    // product
    PRODUCT_NOT_EXIST(100,"商品不存在"),
    PRODUCT_STOCK_EMPTY(101,"库存异常"),

    // order
    ORDER_NOT_EXIST(200,"订单不存在"),
    ORDER_CANCEL_ERROR(201,"订单取消异常"),
    ORDER_UPDATE_ERROR(202,"订单更新异常"),
    ORDER_OWNER_ERROR(203,"该用户没有该订单"),

    // orderDetail
    ORDER_DETAIL_EMPTY(300,"订单详情为空"),

    //weixin
    WECHAT_MP_ERROR(400,"微信公众号部分错误"),

    PARAM_ERROR(401,"参数传入错误")
    ;
    private Integer code;

    private String msg;

    ExceptionEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
