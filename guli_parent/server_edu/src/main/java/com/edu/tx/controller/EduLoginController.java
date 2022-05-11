package com.edu.tx.controller;


import com.edu.tx.entity.vo.paremt.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("edu/user")
public class EduLoginController {
    @PostMapping("login")
    public Result login(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("token","admin");
        return Result.Success(map);
    }
    @GetMapping("info")
    public Result info(){
        HashMap<String, Object> map = new HashMap<>();
        ArrayList<Object> roles = new ArrayList<>();
        roles.add("admin");
        map.put("roles",roles);
        map.put("name","小王");
        map.put("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        return Result.Success(map);
    }
}
