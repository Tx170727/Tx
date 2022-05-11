package com.edu.tx.service;

import com.aliyuncs.exceptions.ClientException;
import com.edu.tx.entity.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author testjava
 * @since 2022-03-24
 */
public interface EduVideoService extends IService<EduVideo> {

    Integer delById(String videoId) throws ClientException;

    List<String> selectVideoIds(String courseId);
}
