package com.tx.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.tx.entity.TOrder;
import com.tx.entity.Vo.Result;
import com.tx.service.TOrderService;
import com.tx.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-04-27
 */
@RestController
@RequestMapping("/cms/order")
public class TOrderController {

    @Autowired
    private TOrderService orderService;

        @PostMapping(value = "createOrder/{courseid}")
    public Result createrOrder(@PathVariable String courseid, HttpServletRequest request){

        String memberIdByJwtToken = JwtUtils.getMemberIdByJwtToken(request);

        String orderId = orderService.create(courseid,memberIdByJwtToken);
        return Result.Success(orderId);
    }
    @GetMapping(value="getOdrderById/{orderId}")
    public Result getOrderById(@PathVariable String orderId){
        TOrder one = orderService.getOne(new LambdaQueryWrapper<TOrder>().eq(TOrder::getOrderNo, orderId));

        return Result.Success(one);
    }
    @GetMapping(value = "isBuyCourse/{courseId}/{memberId}")
    public Boolean isBuyCourse(@PathVariable String memberId,@PathVariable String courseId){
        return  orderService.isBuyCourse(memberId,courseId);

    }
}

