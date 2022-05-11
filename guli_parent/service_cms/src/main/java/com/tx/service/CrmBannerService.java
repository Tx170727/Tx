package com.tx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tx.entity.CrmBanner;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务类
 * </p>
 *
 * @author testjava
 * @since 2022-04-15
 */
public interface CrmBannerService extends IService<CrmBanner> {


    public List<CrmBanner> getImgUrl();
    public List<CrmBanner> getImgUrlList();
}
