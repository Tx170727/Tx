package com.edu.tx.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.edu.tx.entity.EduSubject;
import com.edu.tx.entity.vo.paremt.ExcelParemt;
import com.edu.tx.entity.vo.OneSubjectVo;
import com.edu.tx.entity.vo.TwoSubjectVo;
import com.edu.tx.handler.ExcelListener;
import com.edu.tx.mapper.EduSubjectMapper;
import com.edu.tx.service.EduSubjectService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2022-03-23
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {
    @Autowired
    private EduSubjectMapper mapper;
    @Override
    public List<EduSubject> readExcel(MultipartFile file,EduSubjectService service) {
        try {
            EasyExcel.read(file.getInputStream(), ExcelParemt.class, new ExcelListener(service)).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<OneSubjectVo> getTreeSubject() {
        LambdaQueryWrapper<EduSubject> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(EduSubject::getParentId,"0");
        List<EduSubject> oneSubJect = mapper.selectList(wrapper);// 查出一级名称

        List<OneSubjectVo> onelist = copyOneList(oneSubJect);
        List<OneSubjectVo> ReamlList=new ArrayList<>();
        LambdaQueryWrapper<EduSubject> wrapper1;
        for (OneSubjectVo oneSubjectVo : onelist) {
            wrapper1 = new LambdaQueryWrapper<EduSubject>();
            wrapper1.eq(EduSubject::getParentId, oneSubjectVo.getId());
            List<EduSubject> twoSubjects = mapper.selectList(wrapper1); // 查出一级名称下的二级名称

            oneSubjectVo.setChildren(copyList(twoSubjects));
            ReamlList.add(oneSubjectVo);
        }

        return  ReamlList;
 }
    private List<TwoSubjectVo> copyList(List<EduSubject> list){
        TwoSubjectVo twoSubjectVo;
        List<TwoSubjectVo> twoSubjectVoArrayList = new ArrayList<TwoSubjectVo>();
        for (EduSubject eduSubject : list) {
            twoSubjectVo = new TwoSubjectVo();
            BeanUtils.copyProperties(eduSubject, twoSubjectVo);
            twoSubjectVoArrayList.add(twoSubjectVo);
        }
        return twoSubjectVoArrayList;
    }
    private List<OneSubjectVo> copyOneList(List<EduSubject> list){
        OneSubjectVo oneSubjectVo;
        List<OneSubjectVo> oneSubjectVoArrayList = new ArrayList<OneSubjectVo>();
        for (EduSubject eduSubject : list) {
            oneSubjectVo = new OneSubjectVo();
            BeanUtils.copyProperties(eduSubject, oneSubjectVo);
            oneSubjectVoArrayList.add(oneSubjectVo);
        }
        return oneSubjectVoArrayList;
    }
}
