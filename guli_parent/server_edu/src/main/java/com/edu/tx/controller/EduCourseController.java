package com.edu.tx.controller;


import com.edu.tx.entity.vo.AddCourseVo;
import com.edu.tx.entity.vo.CourseInfoVo;
import com.edu.tx.entity.vo.SureCourseVo;
import com.edu.tx.entity.vo.paremt.CourseParemt;
import com.edu.tx.entity.vo.paremt.Result;
import com.edu.tx.service.EduChapterService;
import com.edu.tx.service.EduCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-03-24
 */
@RestController
@RequestMapping("edu/course")
public class EduCourseController {
    @Autowired
    private EduChapterService service;
    @Autowired
    private EduCourseService eduCourseService;
    @PostMapping(value = "addCourse")
    public Result addCourse(@RequestBody AddCourseVo addCourseVo){
        String id = service.addCourse(addCourseVo);
        return Result.Success(id);
    }
    @GetMapping(value="getCourse/{CourseId}")
    public Result getCourse(@PathVariable String CourseId){
        AddCourseVo addCourseVo = eduCourseService.getCourseByid(CourseId);

        return  Result.Success(addCourseVo);
    }
    @PostMapping(value = "updataCourse")
    public Result updataCourse(@RequestBody AddCourseVo addCourse){
        eduCourseService.updateCourse(addCourse);

        return  Result.Success("成功");
    }

    @GetMapping(value = "getSureCourse/{courseId}")
    public Result getSureCourse(@PathVariable String courseId){
       SureCourseVo sureCourseVo = eduCourseService.getSureCourse(courseId);
       return Result.Success(sureCourseVo);
    }
    @GetMapping(value = "publishCourse/{courseId}")
    public Result publishCourse(@PathVariable String courseId){
        eduCourseService.publishCourse(courseId);
        return  Result.Success(null);
    }

    @PostMapping(value = "getCourseList/{current}")
    public Result getCourseList(@PathVariable int current , @RequestBody CourseParemt CourseParemt){

        HashMap<Object, Object> list = eduCourseService.PageCourselist(current,CourseParemt);
        return  Result.Success(list);
    }
    @GetMapping(value = "del/{courseId}")
    public Result del(@PathVariable String courseId){
        eduCourseService.delCourseById(courseId);

        return Result.Success(null);
    }
    @PostMapping(value="getCourseinfo/{id}")
    public CourseInfoVo getCourseInfo(@PathVariable String id){
        CourseInfoVo courseInfo = eduCourseService.getCourseInfo(id);
        return courseInfo;
    }
}

