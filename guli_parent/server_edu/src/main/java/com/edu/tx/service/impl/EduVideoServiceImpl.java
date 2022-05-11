package com.edu.tx.service.impl;

import com.aliyuncs.exceptions.ClientException;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.edu.tx.client.VodClient;
import com.edu.tx.entity.EduVideo;
import com.edu.tx.entity.vo.paremt.Result;
import com.edu.tx.handler.MyException;
import com.edu.tx.mapper.EduVideoMapper;
import com.edu.tx.service.EduVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2022-03-24
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {

    @Autowired
    private VodClient vodClient;
    @Override
    public Integer delById(String videoId) throws ClientException {
        EduVideo eduVideo = baseMapper.selectById(videoId);
        String videoSourceId = eduVideo.getVideoSourceId();
        Result result = vodClient.delVideo(videoSourceId);
        if(result.getCode()!=200){
            throw new MyException(2500,"出错了");
        }
        int i = baseMapper.deleteById(videoId);
        return i;
    }

    @Override
    public List<String> selectVideoIds(String courseId) {
        return baseMapper.selectByIds(courseId);
    }
}
