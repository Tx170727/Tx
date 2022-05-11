package com.edu.tx.handler;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.edu.tx.entity.EduSubject;
import com.edu.tx.entity.vo.paremt.ExcelParemt;
import com.edu.tx.service.EduSubjectService;

import java.util.Map;

public class ExcelListener extends AnalysisEventListener<ExcelParemt> {

    private EduSubjectService service;
    public ExcelListener(){};
    public ExcelListener(EduSubjectService service){
        this.service=service;
    }

//    读取内容
    @Override
    public void invoke(ExcelParemt data, AnalysisContext context) {
      if(data==null){
          throw new MyException(503,"Excel内容为空");
      }
        EduSubject oneSubject = getOneSubject(service, data.getOneSubjectname());
        if( oneSubject== null){
            EduSubject OneSubject = new EduSubject();
            OneSubject.setTitle(data.getOneSubjectname());
            OneSubject.setParentId("0");
            service.save(OneSubject);
        }
        String id = oneSubject.getId();
        if(getTwoSubject(service, data.getTwoSubjectname(),id) ==null){
            EduSubject TwoSubject = new EduSubject();
            TwoSubject.setTitle(data.getTwoSubjectname());
           TwoSubject.setParentId(id);
            service.save(TwoSubject);
        }

    }
    private static EduSubject getOneSubject(EduSubjectService service,String name){
        LambdaQueryWrapper<EduSubject> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(EduSubject::getTitle,name);
        wrapper.eq(EduSubject::getParentId,"0");
        EduSubject one = service.getOne(wrapper);
        return one;
    }
    private static EduSubject getTwoSubject(EduSubjectService service,String name,String parentId) {
        LambdaQueryWrapper<EduSubject> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(EduSubject::getTitle,name);
        wrapper.eq(EduSubject::getParentId,parentId);
        EduSubject two = service.getOne(wrapper);
        return two;
    }

    // 读取表头
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println(headMap);
    }
// 读取完全后
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        System.out.println("我完成了");
    }
}
