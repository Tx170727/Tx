package com.tx.controller;

import com.tx.entity.Vo.Result;
import com.tx.service.MsmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/cms/msm")
public class msmController {


    @Autowired
    private MsmService msmService;

    @GetMapping(value = "sendCode/{code}")
    public Result SendCode(@PathVariable String code) throws Exception {
        String s = msmService.sendCode(code);
        return Result.Success(s);
    }

}
