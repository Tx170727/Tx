package com.tx.service;

import com.tx.entity.StatisticsDaily;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tx.entity.vo.param.ShowParam;

import java.util.HashMap;

/**
 * <p>
 * 网站统计日数据 服务类
 * </p>
 *
 * @author testjava
 * @since 2022-05-05
 */
public interface StatisticsDailyService extends IService<StatisticsDaily> {

    int countstatistics(String date);

    HashMap<String, Object> show(ShowParam show);
}
