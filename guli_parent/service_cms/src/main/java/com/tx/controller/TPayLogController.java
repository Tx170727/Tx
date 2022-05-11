package com.tx.controller;


import com.tx.entity.Vo.Result;
import com.tx.service.TPayLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <p>
 * 支付日志表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-04-27
 */
@RestController
@RequestMapping("/cms/paylog")
public class TPayLogController {
    @Autowired
    private TPayLogService logService;

    @GetMapping(value = "createNavite/{id}")
    public Result createNavite(@PathVariable String id){
        Map map=logService.createNavite(id);
        System.out.println("123123"+map);
        return Result.Success(map);
    }
    @GetMapping(value = "getPayStatus/{orderNo}")
    public Result getPayStatus(@PathVariable String orderNo){
           Map<String,String> map = logService.getStatus(orderNo);
        System.out.println("+++++++"+map);
            if(map==null){
                return  Result.fail(5000,"支付失败");
            }
            if(map.get("trade_state").equals("SUCCESS")){
                logService.updateOrderStatus(map);
                return Result.Success("");
            }

        return  Result.fail(5000,"支付失败");
    }
}

