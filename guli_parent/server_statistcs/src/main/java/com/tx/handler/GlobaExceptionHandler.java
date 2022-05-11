package com.tx.handler;


import com.tx.entity.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class GlobaExceptionHandler {
//    全局异常处理 减少try catch操作
//    @ExceptionHandler(Exception.class)
//    @ResponseBody
//    public Result soutError(Exception e){
//        log.error(e.getMessage());
//        return Result.fail(501,"异常处理");
//    }
    // 自定义异常处理
    @ExceptionHandler(MyException.class)
    @ResponseBody
    public Result soutError(MyException m){
        return Result.fail(m.getCode(),m.getMessage());
    }
}
