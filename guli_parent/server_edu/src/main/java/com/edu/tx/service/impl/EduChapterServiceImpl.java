package com.edu.tx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.edu.tx.entity.EduChapter;
import com.edu.tx.entity.EduCourse;
import com.edu.tx.entity.EduCourseDescription;
import com.edu.tx.entity.EduVideo;
import com.edu.tx.entity.vo.AddCourseVo;
import com.edu.tx.entity.vo.ChapterVo;
import com.edu.tx.entity.vo.VideoVo;
import com.edu.tx.handler.MyException;
import com.edu.tx.mapper.EduChapterMapper;
import com.edu.tx.mapper.EduCourseMapper;
import com.edu.tx.service.EduChapterService;
import com.edu.tx.service.EduCourseDescriptionService;
import com.edu.tx.service.EduVideoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2022-03-24
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {
    @Autowired
    private EduCourseMapper eduCourseMapper;
    @Autowired
    private EduCourseDescriptionService eduCourseDescriptionService;
    @Autowired
    private EduVideoService eduVideoService;

    @Override
    public String addCourse(AddCourseVo addCourseVo) {
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(addCourseVo,eduCourse);
        int insert = eduCourseMapper.insert(eduCourse);
        if(insert == 0){
            throw new MyException(5001,"添加课程失败");
        }
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
            eduCourseDescription.setId(eduCourse.getId());
            eduCourseDescription.setDescription(addCourseVo.getDescription());
            eduCourseDescriptionService.save(eduCourseDescription);
        return eduCourse.getId();
    }
    @Override
    public List<ChapterVo> getChapters(String chapterId) {
        LambdaQueryWrapper<EduChapter> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(EduChapter::getCourseId,chapterId);
        List<EduChapter> eduChapters = baseMapper.selectList(wrapper);
        LambdaQueryWrapper<EduVideo> videoWrapper = new LambdaQueryWrapper<>();
        List<EduVideo> videolist = eduVideoService.list(videoWrapper);
        List<ChapterVo> RealList = new ArrayList<>();
        ChapterVo chapterVo;
        VideoVo videoVo;
        for (EduChapter eduChapter : eduChapters) {
             chapterVo = new ChapterVo();
            List<VideoVo> videoVoList=new ArrayList<>();
            BeanUtils.copyProperties(eduChapter, chapterVo);
            RealList.add(chapterVo);
            for (EduVideo eduVideo : videolist) {
               if(eduVideo.getChapterId().equals(chapterVo.getId())){
                    videoVo=new VideoVo();
                   BeanUtils.copyProperties(eduVideo,videoVo);
                   videoVoList.add(videoVo);
               }
            }
            chapterVo.setChildren(videoVoList);
        }
            return RealList;
        }
    @Override
    public Boolean delChapterbyId(String chapterId) {
        LambdaQueryWrapper<EduVideo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(EduVideo::getChapterId,chapterId);
        int count = eduVideoService.count(wrapper);
        if(count > 0) {
            throw new MyException(20001,"当前章节下存在小节，无法进行删除操作");
        }{
            int i = baseMapper.deleteById(chapterId);
            return i>0;
         }
    }
}
