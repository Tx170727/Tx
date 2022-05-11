package com.edu.tx.mapper;

import com.edu.tx.entity.EduVideo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 课程视频 Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2022-03-24
 */
public interface EduVideoMapper extends BaseMapper<EduVideo> {

    List<String> selectByIds(String courseId);
}
