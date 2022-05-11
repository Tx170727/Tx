package com.tx.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("service-cms")
public interface cmsClient {
    @GetMapping(value="/cms/ucenter-member/countResign/{day}")
    public Integer countResign(@PathVariable("day") String day);

}
