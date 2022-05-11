package com.edu.tx.controller.front;

import com.edu.tx.entity.vo.paremt.Result;
import com.edu.tx.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = "edu/userTeacher")
public class UserTeacherController {

    @Autowired
    private TeacherService teacherService;

    @GetMapping(value="getTeacherList/{current}")
    public Result GetTeacherList(@PathVariable long current){
        Map<String,Object> map = teacherService.selectTeacherList(current);
        return Result.Success(map);
    }
    @GetMapping(value = "getTeacherInfo/{teacherId}")
    public Result GetTeacherInfo(@PathVariable String teacherId){

        Map<String,Object> teacherInfo = teacherService.selectTeacherInfo(teacherId);

        return Result.Success(teacherInfo);
    }

}
