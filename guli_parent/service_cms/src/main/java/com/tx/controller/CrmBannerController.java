package com.tx.controller;


import com.tx.entity.CrmBanner;
import com.tx.entity.Vo.Result;
import com.tx.service.CrmBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-04-15
 */
@RestController
@RequestMapping("/cms/banner")
public class CrmBannerController {
    @Autowired
    private CrmBannerService crmBannerService;

    @GetMapping(value = "getBanner")
    public Result GetBanner(){
        List<CrmBanner> imgUrlList = crmBannerService.getImgUrl();
        return Result.Success(imgUrlList);
    }
    @PostMapping(value = "saveBanner")
    public Result SaveBanner(@RequestBody CrmBanner crmBanner){
        boolean save = crmBannerService.save(crmBanner);
        return Result.Success(save);
    }
    @GetMapping(value = "delBanner/{id}")
    public Result DelBanner(@PathVariable String id){
        boolean b = crmBannerService.removeById(id);
        return Result.Success(b);
    }
}

