package com.tx.controller;


import com.tx.entity.UcenterMember;
import com.tx.entity.Vo.RegisterParemt;
import com.tx.entity.Vo.Result;
import com.tx.service.UcenterMemberService;
import com.tx.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.NoSuchAlgorithmException;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-04-20
 */
@RestController
@RequestMapping("/cms/ucenter-member")
public class UcenterMemberController {
    @Autowired
    private UcenterMemberService ucenterMemberService;

    @PostMapping(value = "login")
    public Result Login(@RequestBody UcenterMember ucenterMember){
        String token=ucenterMemberService.login(ucenterMember);

        return Result.Success(token);
    }
    @PostMapping(value = "register")
    public Result Register(@RequestBody RegisterParemt register) throws NoSuchAlgorithmException {
       Boolean flag=ucenterMemberService.register(register);
        return Result.Success("注册成功！");
    }
    @GetMapping(value = "getJwtInfo")
    public Result GetJwtInfo(HttpServletRequest request){
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        UcenterMember byId = ucenterMemberService.getById(memberId);
        return Result.Success(byId);
    }
    @GetMapping(value="countResign/{day}")
    public Integer countResign(@PathVariable String day) {
        Integer count = ucenterMemberService.countResign(day);
        return count;
    }


}

