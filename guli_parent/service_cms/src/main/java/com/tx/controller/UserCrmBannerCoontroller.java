package com.tx.controller;

import com.tx.entity.CrmBanner;
import com.tx.entity.Vo.Result;
import com.tx.service.CrmBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/cms/Userbanner")
public class UserCrmBannerCoontroller {
    @Autowired
    private CrmBannerService crmBannerService;

    @GetMapping(value = "getBanner")
    public Result GetBanner(){
        List<CrmBanner> imgUrlList = crmBannerService.getImgUrl();
        return Result.Success(imgUrlList);
    }

}
