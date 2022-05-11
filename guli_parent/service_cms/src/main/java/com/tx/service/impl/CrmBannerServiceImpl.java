package com.tx.service.impl;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tx.entity.CrmBanner;
import com.tx.mapper.CrmBannerMapper;
import com.tx.service.CrmBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 首页banner表 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2022-04-15
 */
@Service
public class CrmBannerServiceImpl extends ServiceImpl<CrmBannerMapper, CrmBanner> implements CrmBannerService {
    @Autowired
    private CrmBannerMapper crmBannerMapper;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Override
    public List<CrmBanner> getImgUrl() {
        Boolean islist = redisTemplate.hasKey("Bannerlist");
        if(islist){
            List<String> bannerlist = redisTemplate.opsForList().range("Bannerlist", 0, -1);
            List<CrmBanner> BannerList = new ArrayList<>();
            for (String s : bannerlist) {
                 BannerList.add(JSON.parseObject(s,CrmBanner.class));

            }
            return  BannerList;
        }
        {
            LambdaQueryWrapper<CrmBanner> Wrapper = new LambdaQueryWrapper<>();
            Wrapper.last("limit 5");
            List<CrmBanner> crmBanners = crmBannerMapper.selectList(Wrapper);
            for (CrmBanner crmBanner : crmBanners) {
               redisTemplate.opsForList().leftPush("Bannerlist", JSON.toJSONString(crmBanner));
            }
            redisTemplate.expire("Bannerlist", 5*60*1000, TimeUnit.MILLISECONDS);
            return crmBanners;
        }
    }
    @Override
    public List<CrmBanner> getImgUrlList() {
        List<CrmBanner> crmBanners = crmBannerMapper.selectList(null);
        return crmBanners;
    }
}
