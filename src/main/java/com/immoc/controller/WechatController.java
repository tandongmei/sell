package com.immoc.controller;

import com.immoc.enums.ExceptionEnum;
import com.immoc.exception.SellException;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URLEncoder;

/**
 * Created by tan.dongmei on 2017/9/22 0022.
 */
@Controller
@RequestMapping(value = "wechat")
public class WechatController {

    @Autowired
    private WxMpService wxMpService;

    @GetMapping(value = "authorize")
    public String authorize(@RequestParam("returnUrl") String returnUrl){
        String redirectURI = "http://meta.natapp1.cc/sell/wechat/userInfo";
        String redirectUrl = wxMpService.oauth2buildAuthorizationUrl(redirectURI, WxConsts.OAUTH2_SCOPE_USER_INFO, URLEncoder.encode(returnUrl));
        return "redirect:"+redirectUrl;
    }

    @GetMapping(value = "userInfo")
    public String userInfo(@RequestParam("code") String code,@RequestParam("state") String returnUrl){
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken = new WxMpOAuth2AccessToken();
        try {
            wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
        } catch (WxErrorException e) {
            e.printStackTrace();
            throw new SellException(ExceptionEnum.WECHAT_MP_ERROR);
        }
        String openId = wxMpOAuth2AccessToken.getOpenId();
//        System.out.println("openid:"+openId);
//        System.out.println("state:"+returnUrl);
        return "redirect:" + returnUrl + "?openid=" + openId;
    }

}
