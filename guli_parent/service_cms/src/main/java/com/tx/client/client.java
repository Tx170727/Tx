package com.tx.client;

import com.tx.entity.Vo.CourseInfoVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Component
@FeignClient("service-edu")
public interface client {

    @PostMapping(value="/edu/course/getCourseinfo/{id}")
    public CourseInfoVo getCourseInfo(@PathVariable("id") String id);
}
