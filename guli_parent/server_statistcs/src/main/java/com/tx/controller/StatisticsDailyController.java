package com.tx.controller;


import com.tx.entity.vo.Result;
import com.tx.entity.vo.param.ShowParam;
import com.tx.service.StatisticsDailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-05-05
 */
@RestController
@RequestMapping("/statistics")
public class StatisticsDailyController {
    @Autowired
    private StatisticsDailyService service;
    @Autowired
    private com.tx.client.cmsClient cmsClient;
    @GetMapping("/countStatistics/{date}")
    public Result countStatistics(@PathVariable String date){
        int countstatistics = service.countstatistics(date);
        return Result.Success(countstatistics);
    }
    @PostMapping("/show")
    public Result show(@RequestBody ShowParam show){
        HashMap<String,Object> map=service.show(show);
        return  Result.Success(map);
    }
}

