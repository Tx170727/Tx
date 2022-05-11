package com.edu.tx.entity.vo.paremt;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Result {
    private Integer code;
    private Boolean success;
    private String message;
    private Object data;
    public static Result Success(Object data){
        return new Result(200, true,  "成功",data);
    }
    public static Result fail(Integer code,String message) {
        return new Result(code,false,message,null);
    }

}