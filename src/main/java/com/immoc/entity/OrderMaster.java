package com.immoc.entity;

import com.immoc.enums.OrderStatusEnum;
import com.immoc.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by tandongmei on 2017/9/6.
 */
@Entity
@DynamicUpdate
@Data
public class OrderMaster {

    @Id
    private String orderId;

    /** 买家名字*/
    private String buyerName;

    /** 买家电话*/
    private String buyerPhone;

    /** 买家地址*/
    private String buyerAddress;

    /** 买家微信openid*/
    private String buyerOpenid;

    /** 订单总金额*/
    private BigDecimal orderAmount;

    /** 订单状态, 默认为新下单*/
    private Integer orderStatus = OrderStatusEnum.NEW.getCode();

    /** 支付状态, 默认未支付*/
    private Integer payStatus = PayStatusEnum.WAIT.getCode();

    private Date createTime;

    private Date updateTime;

    }
