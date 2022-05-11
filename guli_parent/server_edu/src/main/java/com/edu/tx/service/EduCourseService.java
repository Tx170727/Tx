package com.edu.tx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.edu.tx.entity.EduCourse;
import com.edu.tx.entity.vo.AddCourseVo;
import com.edu.tx.entity.vo.CourseInfoVo;
import com.edu.tx.entity.vo.SureCourseVo;
import com.edu.tx.entity.vo.paremt.CourseParemt;
import com.edu.tx.entity.vo.paremt.UserCourseParemt;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author testjava
 * @since 2022-03-24
 */
public interface EduCourseService extends IService<EduCourse> {

    HashMap<String, Object> getEduCoursePage(UserCourseParemt paremt,long current);

    AddCourseVo getCourseByid(String courseId);

    void updateCourse(AddCourseVo addCourse);

    SureCourseVo getSureCourse(String courseId);

    void publishCourse(String courseId);

    HashMap<Object, Object> PageCourselist(int current, CourseParemt courseParemt);

    void delCourseById(String courseId);

    List<EduCourse> getCourseList();

    CourseInfoVo getCourseInfo(String courseId);
}
