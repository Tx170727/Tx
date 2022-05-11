package com.edu.tx.controller;


import com.edu.tx.entity.Teacher;
import com.edu.tx.entity.vo.paremt.PageParemt;
import com.edu.tx.entity.vo.paremt.Result;
import com.edu.tx.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-03-16
 */
@RestController
@RequestMapping("/edu/teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @GetMapping(value="findAll")
    public Result findAllTeachers(){
        return  Result.Success( teacherService.list(null));
    }
    @GetMapping(value="delete/{id}")
    public Result deleteTeacher(@PathVariable String id){
       return    Result.Success(teacherService.removeById(id));
    }

    // 分页查询
    @PostMapping(value = "page/{current}")
    public Result pageTeacher(@PathVariable long current,@RequestBody(required = false) PageParemt pageParemt){
        HashMap<Object, Object> map = teacherService.PageTeacher(current, pageParemt);
        return Result.Success(map);
    }
    @PostMapping(value = "inserTeacher")
    public Result insertTeacher(@RequestBody Teacher teacher){
        boolean save = teacherService.save(teacher);
        if(save){
            return Result.Success(save);
        }{
            return Result.fail(500,"添加失败");
        }
    }
    @GetMapping(value="get/{id}")
    public Result getTeacherById(@PathVariable String id){
        Teacher byId = teacherService.getById(id);
        return Result.Success(byId);
    }
    @PostMapping(value = "updateTeacher")
    public Result updateTeacher(@RequestBody Teacher teacher){
        boolean b = teacherService.updateById(teacher);
        if (b){
            return Result.Success(b);
        }{
            return Result.fail(500,"更新失败");
        }
    }
}

