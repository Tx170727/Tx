package com.edu.tx.client;

import com.aliyun.oss.ClientException;
import com.edu.tx.entity.vo.paremt.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@FeignClient(value = "service-oss" , fallback = VodClientHystrsix.class)//
public interface VodClient {

    @GetMapping(value = "/oss/delVideo/{videoId}")
    public Result delVideo(@PathVariable String videoId) throws com.aliyuncs.exceptions.ClientException ;

    @PostMapping(value = "/oss/delVideoList")
    public Result delVideoList(@RequestParam("videoId") List<String> videoId) throws ClientException;
}
