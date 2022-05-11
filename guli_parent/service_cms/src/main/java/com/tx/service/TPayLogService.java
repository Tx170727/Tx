package com.tx.service;

import com.tx.entity.TPayLog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 支付日志表 服务类
 * </p>
 *
 * @author testjava
 * @since 2022-04-27
 */
public interface TPayLogService extends IService<TPayLog> {

    Map createNavite(String id);

    Map<String,String> getStatus(String orderNo);

    void updateOrderStatus(Map<String, String> map);
}
