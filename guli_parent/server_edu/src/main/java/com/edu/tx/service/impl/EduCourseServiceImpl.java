package com.edu.tx.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.edu.tx.client.VodClient;
import com.edu.tx.entity.EduChapter;
import com.edu.tx.entity.EduCourse;
import com.edu.tx.entity.EduCourseDescription;
import com.edu.tx.entity.EduVideo;
import com.edu.tx.entity.vo.AddCourseVo;
import com.edu.tx.entity.vo.CourseInfoVo;
import com.edu.tx.entity.vo.SureCourseVo;
import com.edu.tx.entity.vo.paremt.CourseParemt;
import com.edu.tx.entity.vo.paremt.UserCourseParemt;
import com.edu.tx.handler.MyException;
import com.edu.tx.mapper.EduCourseMapper;
import com.edu.tx.service.EduChapterService;
import com.edu.tx.service.EduCourseDescriptionService;
import com.edu.tx.service.EduCourseService;
import com.edu.tx.service.EduVideoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2022-03-24
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {
    @Autowired
    private EduCourseDescriptionService eduCourseDescriptionService;
    @Autowired
    private EduVideoService eduVideoService;
    @Autowired
    private EduChapterService eduChapterService;
    @Autowired
    private VodClient vodClient;
    @Autowired
    private EduCourseMapper mapper;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public HashMap<String, Object> getEduCoursePage(UserCourseParemt paremt,long current) {
        LambdaQueryWrapper<EduCourse> wrapper = new LambdaQueryWrapper<>();
        if(!StringUtils.isEmpty(paremt.getSubjectParentId())){
            wrapper.eq(EduCourse::getSubjectParentId,paremt.getSubjectParentId());
            if(!StringUtils.isEmpty(paremt.getSubjectId())){
                wrapper.eq(EduCourse::getSubjectId,paremt.getSubjectId());
            }
        }

        if( !StringUtils.isEmpty( paremt.getBuyCountSort())){
            wrapper.orderByDesc(EduCourse::getBuyCount);
        }
        if( !StringUtils.isEmpty(paremt.getGmtCreateSort())){
            wrapper.orderByDesc(EduCourse::getGmtCreate);
        }{
            wrapper.orderByAsc(EduCourse::getGmtCreate);
        }
        if(!StringUtils.isEmpty(paremt.getPriceSort())){
            wrapper.orderByDesc(EduCourse::getPrice);
        }
        Page<EduCourse> eduCoursePage = new Page<>(current,8);
        mapper.selectPage(eduCoursePage, wrapper);

        HashMap<String, Object> map = new HashMap<>();

        map.put("record",eduCoursePage.getRecords());
        map.put("total",eduCoursePage.getTotal());
        map.put("current",eduCoursePage.getCurrent());
        return map;
    }

    @Override
    public AddCourseVo getCourseByid(String courseId) {
        EduCourse eduCourse = baseMapper.selectById(courseId);
        AddCourseVo addCourseVo = new AddCourseVo();
        BeanUtils.copyProperties(eduCourse, addCourseVo);
        EduCourseDescription byId = eduCourseDescriptionService.getById(courseId);
        addCourseVo.setDescription(byId.getDescription());
        return addCourseVo;
    }

    @Override
    public void updateCourse(AddCourseVo addCourse) {

        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(addCourse, eduCourse);
        int update = baseMapper.updateById(eduCourse);
        if(update == 0){
            throw new MyException();
        }
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        eduCourseDescription.setDescription(addCourse.getDescription());
        eduCourseDescriptionService.update(eduCourseDescription
                , new LambdaQueryWrapper<EduCourseDescription>().eq(EduCourseDescription::getId,addCourse.getId()));

    }

    @Override
    public SureCourseVo getSureCourse(String courseId) {
        return baseMapper.getCourseByCourseId(courseId);
    }

    @Override
    public void publishCourse(String courseId) {
            baseMapper.publishCourse(courseId);
    }

    @Override
    public HashMap<Object, Object> PageCourselist(int current, CourseParemt courseParemt) {
        Page<EduCourse> page = new Page<>(current,10);
        LambdaQueryWrapper<EduCourse> wrapper = new LambdaQueryWrapper<>();
        if(!StringUtils.isBlank(courseParemt.getTitle())){
            wrapper.like(EduCourse::getTitle,courseParemt.getTitle());
        }
        if(!StringUtils.isBlank(courseParemt.getStatus())){
            wrapper.eq(EduCourse::getStatus,courseParemt.getStatus());
        }
        Page<EduCourse> eduCoursePage = baseMapper.selectPage(page, wrapper);

        HashMap<Object, Object> map = new HashMap();
        map.put("list",eduCoursePage.getRecords());
        map.put("total",eduCoursePage.getTotal());
        return map;
    }

    @Override
    public void delCourseById(String courseId) {
         List<String> videoIds =  eduVideoService.selectVideoIds(courseId);
        vodClient.delVideoList(videoIds);
        eduChapterService.remove(new LambdaQueryWrapper<EduChapter>().eq(EduChapter::getCourseId,courseId));
        eduCourseDescriptionService.removeById(courseId);
        eduVideoService.remove(new LambdaQueryWrapper<EduVideo>().eq(EduVideo::getCourseId,courseId));
        baseMapper.deleteById(courseId);
    }

    @Override
    public List<EduCourse> getCourseList() {
        Boolean eduCourseList = redisTemplate.hasKey("eduCourseList");
        if (eduCourseList) {
            List<String> eduCourseList1 = redisTemplate.opsForList().range("eduCourseList", 0, -1);
            List<EduCourse> eduCourses = new ArrayList<>();
            for (String o : eduCourseList1) {
                eduCourses.add(JSON.parseObject(o,EduCourse.class));
            }
            return eduCourses;
        }
        {
            LambdaQueryWrapper<EduCourse> wrapper = new LambdaQueryWrapper<>();
            wrapper.orderByAsc(EduCourse::getViewCount);
            wrapper.last("limit 8");
            List<EduCourse> eduCourses = mapper.selectList(wrapper);
            for (EduCourse eduCours : eduCourses) {
                redisTemplate.opsForList().leftPush("eduCourseList",JSON.toJSONString(eduCours));
            }
            redisTemplate.expire("eduCourseList", 5*60*1000, TimeUnit.MILLISECONDS);
            return eduCourses;
        }
    }

    @Override
    public CourseInfoVo getCourseInfo(String courseId) {
        CourseInfoVo courseInfo=mapper.getCourseInfo(courseId);

        return courseInfo;
    }
}
