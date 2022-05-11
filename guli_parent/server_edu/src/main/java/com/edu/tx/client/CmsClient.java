package com.edu.tx.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="service-cms",fallback =CmsClientHystrsix.class)//
public interface CmsClient {
    @GetMapping(value = "/cms/order/isBuyCourse/{courseId}/{memberId}")
    public Boolean isBuyCourse( @PathVariable("memberId") String memberId, @PathVariable("courseId") String courseId);

}
