package com.edu.tx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.edu.tx.entity.EduChapter;
import com.edu.tx.entity.vo.AddCourseVo;
import com.edu.tx.entity.vo.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author testjava
 * @since 2022-03-24
 */
public interface EduChapterService extends IService<EduChapter> {

    String addCourse(AddCourseVo addCourseVo);

    List<ChapterVo> getChapters(String chapterId);

    Boolean delChapterbyId(String chapterId);
}
