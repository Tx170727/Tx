package com.edu.tx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.edu.tx.entity.EduSubject;
import com.edu.tx.entity.vo.OneSubjectVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author testjava
 * @since 2022-03-23
 */
public interface EduSubjectService extends IService<EduSubject> {

    List<EduSubject> readExcel(MultipartFile file,EduSubjectService service);

    List<OneSubjectVo> getTreeSubject();
}
