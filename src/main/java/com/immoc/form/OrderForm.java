package com.immoc.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by Administrator on 2017/9/13 0013.
 */
@Data
public class OrderForm {

    @NotEmpty(message = "用户名不为空")
    private String name;

    @NotEmpty(message = "号码不为空")
    private String phone;

    @NotEmpty(message = "地址不为空")
    private String address;

    @NotEmpty(message = "买家openid不为空")
    private String openid;

    @NotEmpty(message = "购物车不为空")
    private String items;

}
