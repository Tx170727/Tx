package com.edu.tx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.edu.tx.entity.Teacher;
import com.edu.tx.entity.vo.paremt.PageParemt;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author testjava
 * @since 2022-03-16
 */
public interface TeacherService extends IService<Teacher> {
    HashMap<Object, Object> PageTeacher(long current,PageParemt pageParemt);

    List<Teacher> getHostteacherList();

    Map<String,Object> selectTeacherList(long current);

    Map<String, Object> selectTeacherInfo(String teacherId);
}
