package com.edu.tx.client;

import com.aliyuncs.exceptions.ClientException;
import com.edu.tx.entity.vo.paremt.Result;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class VodClientHystrsix implements VodClient{
    @Override
    public Result delVideo(String videoId) throws ClientException {
        return Result.fail(20000,"删除视频出错");
    }

    @Override
    public Result delVideoList(List<String> videoId) throws com.aliyun.oss.ClientException {
        return Result.fail(20000,"删除视频出错");
    }
}
