package com.edu.tx.mapper;

import com.edu.tx.entity.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.edu.tx.entity.vo.CourseInfoVo;
import com.edu.tx.entity.vo.SureCourseVo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2022-03-24
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {
    public SureCourseVo getCourseByCourseId(String courseId);
    public void publishCourse(String courseId);

    public CourseInfoVo getCourseInfo(@Param("courseId") String courseId);
 }
