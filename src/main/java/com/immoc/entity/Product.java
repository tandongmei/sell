package com.immoc.entity;

import com.immoc.enums.ProductStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by tandongmei on 2017/9/6.
 */
@Entity
@DynamicUpdate
@Table(name = "product_info")
@Data
public class Product {

    @Id
    private String productId;

    /** 商品名称*/
    private String productName;

    /** 单价*/
    private BigDecimal productPrice;

    /** 库存*/
    private Integer productStock;

    /** 描述*/
    private String productDescription;

    /** 小图*/
    private String productIcon;

    /** 商品状态,0正常1下架*/
    private Integer productStatus = ProductStatusEnum.UP.getCode();

    /** 类目编号*/
    private Integer categoryType;

    private Date createTime;

    private Date updateTime;

    }
