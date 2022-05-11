package com.edu.tx.controller;


import com.edu.tx.entity.vo.OneSubjectVo;
import com.edu.tx.entity.vo.paremt.Result;
import com.edu.tx.service.EduSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-03-23
 */
@RestController
@RequestMapping("/edu/subject")
public class EduSubjectController {
    @Autowired
    private EduSubjectService service;
    @PostMapping(value = "readExcel")
    public Result ReadExcel(MultipartFile file) throws IOException {
       service.readExcel(file,service);
        return  Result.Success("");
    }

    @GetMapping(value = "getTreeSubject")
    public Result getTreeSubject(){

//        HashMap<String, List<TwoSubjectVo>>  map = service.getTreeSubject();
        List<OneSubjectVo> treeSubject = service.getTreeSubject();
        return  Result.Success(treeSubject);
    }
}

