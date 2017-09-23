package com.immoc.dto;

import com.immoc.entity.OrderDetail;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/9/10 0010.
 */
@Data
public class OrderDTO {

    private String orderId;

    /** 买家名字*/
    private String buyerName;

    /** 买家电话*/
    private String buyerPhone;

    /** 买家地址*/
    private String buyerAddress;

    /** 买家微信openid*/
    private String buyerOpenid;

    /** 订单状态, 默认为新下单*/
    private Integer orderStatus;

    /** 订单总金额*/
    private BigDecimal orderAmount;

    /** 支付状态, 默认未支付*/
    private Integer payStatus;

    private Date createTime;

    private Date updateTime;

    /** 订单详情集合*/
    private List<OrderDetail> orderDetailList;

}
