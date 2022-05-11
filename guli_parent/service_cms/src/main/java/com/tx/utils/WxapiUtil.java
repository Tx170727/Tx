package com.tx.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class WxapiUtil implements InitializingBean {
    @Value("${wx.open.appid}")
    private String appid;
    @Value("${wx.open.appsecret}")
    private  String appsecret;
    @Value("${wx.open.redirecturl}")
    private String redirecturl;

    public static String id;
    public static String secret;
    public static String redirect;
    @Override
    public void afterPropertiesSet() throws Exception {
        id=appid;
        secret=appsecret;
        redirect=redirecturl;
    }
}
