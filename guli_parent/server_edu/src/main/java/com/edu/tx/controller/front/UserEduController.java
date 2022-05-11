package com.edu.tx.controller.front;

import com.edu.tx.entity.EduCourse;
import com.edu.tx.entity.Teacher;
import com.edu.tx.entity.vo.paremt.Result;
import com.edu.tx.service.EduCourseService;
import com.edu.tx.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("edu/UserEdu")
public class UserEduController {
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private EduCourseService eduCourseService;

    @GetMapping(value="hostTeacherlist")
    public Result HostTeacherlist(){
         List<Teacher> teacherList = teacherService.getHostteacherList();
            return Result.Success(teacherList);
    }
    @GetMapping(value = "hostCourseList")
    public Result HostCourseList(){
        List<EduCourse> courses = eduCourseService.getCourseList();
        return  Result.Success(courses);
    }
}
