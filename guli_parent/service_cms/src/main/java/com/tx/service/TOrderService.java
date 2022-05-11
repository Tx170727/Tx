package com.tx.service;

import com.tx.entity.TOrder;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author testjava
 * @since 2022-04-27
 */
public interface TOrderService extends IService<TOrder> {

    

    String create(String courseid, String memberIdByJwtToken);

    Boolean isBuyCourse(String memberId, String courseId);
}
