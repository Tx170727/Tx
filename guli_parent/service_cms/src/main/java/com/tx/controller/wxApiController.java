package com.tx.controller;


import com.google.gson.Gson;
import com.tx.entity.UcenterMember;
import com.tx.service.UcenterMemberService;
import com.tx.utils.HttpClientUtils;
import com.tx.utils.JwtUtils;
import com.tx.utils.WxapiUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;

@Controller
@RequestMapping(value = "/api/ucenter/wx")
public class wxApiController {
    @Autowired
    private UcenterMemberService ucenterMemberService;

    @GetMapping(value = "callback")
    public String Callback(String code){
        try {
            //1 获取code值，临时票据，类似于验证码
            //2 拿着code请求 微信固定的地址，得到两个值 accsess_token 和 openid
            String baseAccessTokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token" +
                    "?appid=%s" +
                    "&secret=%s" +
                    "&code=%s" +
                    "&grant_type=authorization_code";
            String format = String.format(baseAccessTokenUrl,
                    WxapiUtil.id,
                    WxapiUtil.secret,
                    code
            );

            String Info = HttpClientUtils.get(format);
            Gson gson = new Gson();
            HashMap mapInfo = gson.fromJson(Info, HashMap.class);
            String access_token = (String) mapInfo.get("access_token");
            String openid = (String) mapInfo.get("openid");



            UcenterMember member = ucenterMemberService.getUserByOpenid(openid);
            if(member == null){
                //3 拿着得到accsess_token 和 openid，再去请求微信提供固定的地址，获取到扫描人信息
                //访问微信的资源服务器，获取用户信息
                String baseUserInfoUrl = "https://api.weixin.qq.com/sns/userinfo" +
                        "?access_token=%s" +
                        "&openid=%s";
                String InfoUrl = String.format(baseUserInfoUrl,
                        access_token,
                        openid
                );
                String UserInfo = HttpClientUtils.get(InfoUrl);
                HashMap UserInfoMap = gson.fromJson(UserInfo, HashMap.class);
                String UserInfoopenid = (String) UserInfoMap.get("openid");
                String nickname = (String) UserInfoMap.get("nickname");
                String headimgurl = (String) UserInfoMap.get("headimgurl");
                member = new UcenterMember();
                member.setNickname(nickname);
                member.setOpenid(UserInfoopenid);
                member.setAvatar(headimgurl);
                ucenterMemberService.save(member);
                String jwtToken = JwtUtils.getJwtToken(member.getId(), nickname);

                return "redirect:http://localhost:3000?token="+jwtToken;
            }
            String jwtToken = JwtUtils.getJwtToken(member.getId(), member.getNickname());
            return "redirect:http://localhost:3000?token="+jwtToken;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:http://localhost:3000";
    }

    @GetMapping(value = "getCode")
    public  String GetCode() throws UnsupportedEncodingException {
        // 微信开放平台授权baseUrl
        String baseUrl = "https://open.weixin.qq.com/connect/qrconnect" +
        "?appid=%s" +
        "&redirect_uri=%s" +
        "&response_type=code" +
        "&scope=snsapi_login" +
        "&state=%s" +
        "#wechat_redirect";
        String redirectUrl = WxapiUtil.redirect;
        String encode = URLEncoder.encode(redirectUrl, "UTF-8");
        String url = String.format(baseUrl, WxapiUtil.id, encode, "code");

        return "redirect:"+url;
    }


}
