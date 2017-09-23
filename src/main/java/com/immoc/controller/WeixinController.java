package com.immoc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by tan.dongmei on 2017/9/22 0022.
 */
@RestController
@RequestMapping("weixin")
public class WeixinController {

    private final Logger logger = LoggerFactory.getLogger(BuyerProductController.class);

    @RequestMapping("auth")
    public void auth(@RequestParam String code){
        logger.info("进入auth方法");
        logger.info("code:"+code);
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx67b1b4d71f8ccade&secret=61e06c0395cd257cccea02f8e2c8f307&code="+code+"&grant_type=authorization_code";
        String result = restTemplate.getForObject(url,String.class);
        System.out.print("access_token:"+result);
    }
}
