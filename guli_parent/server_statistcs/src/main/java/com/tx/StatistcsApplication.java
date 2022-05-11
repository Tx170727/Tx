package com.tx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
@EnableFeignClients
@EnableHystrix
@EnableScheduling
@MapperScan("com.tx.mapper")
public class StatistcsApplication {
    public static void main(String[] args) {
        SpringApplication.run(StatistcsApplication.class,args);
    }
}
