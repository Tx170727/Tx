package com.edu.tx.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.edu.tx.entity.EduCourse;
import com.edu.tx.entity.Teacher;
import com.edu.tx.entity.vo.paremt.PageParemt;
import com.edu.tx.mapper.EduCourseMapper;
import com.edu.tx.mapper.TeacherMapper;
import com.edu.tx.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2022-03-16
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {
    @Autowired
    private TeacherMapper mapper;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private EduCourseMapper eduCourseMapper;
    @Override
    public HashMap<Object, Object> PageTeacher(long current,PageParemt pageParemt) {

        Page<Teacher> teacherPage = new Page<Teacher>(current,5);

        LambdaQueryWrapper<Teacher> wrapper = new LambdaQueryWrapper<>();
        if(pageParemt.getName() != null) {
            wrapper.like(Teacher::getName, pageParemt.getName());
        }
        if(pageParemt.getLevel() != null){
            wrapper.eq(Teacher::getLevel, pageParemt.getLevel());
        }
        if (pageParemt.getStart()!=null){
            wrapper.gt(Teacher::getGmtCreate, pageParemt.getStart());
        }
        if (pageParemt.getEnd()!=null){
            wrapper.le(Teacher::getGmtModified,pageParemt.getEnd());
        }
        wrapper.orderByDesc(Teacher::getGmtCreate);
        mapper.selectPage(teacherPage,wrapper);
        HashMap<Object, Object> map = new HashMap<>();
        map.put("total",teacherPage.getTotal());
        map.put("data",teacherPage.getRecords());
        return map;
    }

    @Override
    public List<Teacher> getHostteacherList() {
        Boolean teacherList = redisTemplate.hasKey("teacherList");
        if(teacherList){
            List<Teacher> teacherArrayList = new ArrayList<Teacher>();
            List<String> teacherList1 = redisTemplate.opsForList().range("teacherList", 0, -1);
            for (String s : teacherList1) {
                teacherArrayList.add(JSON.parseObject(s,Teacher.class));
            }
            return  teacherArrayList;
        }{
        LambdaQueryWrapper<Teacher> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Teacher::getLevel,2);
        wrapper.last("limit 4");
        List<Teacher> teachers = mapper.selectList(wrapper);
            for (Teacher teacher : teachers) {
                redisTemplate.opsForList().leftPush("teacherList",JSON.toJSONString(teacher));
            }
            redisTemplate.expire("teacherList",5*60*1000, TimeUnit.MILLISECONDS);
            return teachers;
        }
    }

    @Override
    public Map<String,Object> selectTeacherList(long current) {
        Page<Teacher> teacherPage = new Page<>(current,8);
        LambdaQueryWrapper<Teacher> wrapper = new LambdaQueryWrapper<>();
        mapper.selectPage(teacherPage,null);
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("records",teacherPage.getRecords());
        map.put("pages",teacherPage.getPages());
        map.put("current",teacherPage.getCurrent());
        map.put("size",teacherPage.getSize());
        map.put("total",teacherPage.getTotal());
        map.put("hasNext",teacherPage.hasNext());
        map.put("hasPrevious",teacherPage.hasPrevious());
        return map;
    }

    @Override
    public Map<String, Object> selectTeacherInfo(String teacherId) {
        Teacher teacher = mapper.selectOne(new LambdaQueryWrapper<Teacher>().eq(Teacher::getId, teacherId));

        List<EduCourse> eduCourses = eduCourseMapper.selectList(
                                                new LambdaQueryWrapper<EduCourse>().eq(EduCourse::getTeacherId, teacherId));
        HashMap<String, Object> map = new HashMap<>();
        map.put("teacher",teacher);
        map.put("course",eduCourses);


        return map;
    }


}
