package com.tx.service.impl;

import com.tx.sample.Sample;
import com.tx.service.MsmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class MsmServiceImpl implements MsmService {
    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public String sendCode(String code) throws Exception {
        Boolean aBoolean = redisTemplate.hasKey(code);
        if (aBoolean) {
            return "请勿频繁发送验证码！";
        }
        {
            String aliyunCode = Sample.getAliyunCode(code);
            redisTemplate.opsForValue().set(code, aliyunCode, 5*60*1000, TimeUnit.MILLISECONDS);
            return "验证码发送成功，验证码有效期为五分钟！";
        }
    }

}
