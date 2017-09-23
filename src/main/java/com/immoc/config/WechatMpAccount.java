package com.immoc.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by tan.dongmei on 2017/9/23 0023.
 */
@Data
@Component
@ConfigurationProperties(prefix = "wechat")
public class WechatMpAccount {

    private String appId;

    private String appSecret;
}
