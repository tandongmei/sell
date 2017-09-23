package com.immoc.entity;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by tandongmei on 2017/9/6.
 */
@Entity
@DynamicUpdate
@Table(name = "seller_info")
public class Seller {

    @Id
    private String id;

    private String username;

    private String password;

    /**
     * '微信openid'
     */
    private String openId;

    private Date createTime;

    private Date updateTime;

    }
