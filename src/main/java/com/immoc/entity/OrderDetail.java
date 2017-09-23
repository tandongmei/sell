package com.immoc.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by tandongmei on 2017/9/6.
 */
@Entity
@DynamicUpdate
@Data
public class OrderDetail {

    @Id
    private String detailId;

    private String orderId;

    private String productId;

    /** '商品名称'*/
    private String productName;

    /** '当前价格,单位分'*/
    private BigDecimal productPrice;

    /** '数量'*/
    private Integer productQuantity;

    /** '小图'*/
    private String productIcon;

    private Date createTime;

    private Date updateTime;

    }
