package com.edu.tx.controller;

import com.edu.tx.entity.EduChapter;
import com.edu.tx.entity.vo.ChapterVo;
import com.edu.tx.entity.vo.paremt.Result;
import com.edu.tx.service.EduChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "edu/chapter")
public class EduChapterController {

    @Autowired
    private EduChapterService eduChapterService;
    @GetMapping(value="get/{chapterId}")
    public Result getChapters(@PathVariable String chapterId ) {
        List<ChapterVo> chapterVoList = eduChapterService.getChapters(chapterId);
        return Result.Success(chapterVoList);
    }

    @PostMapping(value = "saveChapter")
    public Result saveChapter(@RequestBody EduChapter eduChapter){
        boolean save = eduChapterService.save(eduChapter);
        return Result.Success(null);
    }

    @GetMapping(value = "{chapterId}")
    public Result getChapter(@PathVariable String chapterId){
        EduChapter byId = eduChapterService.getById(chapterId);
        return Result.Success(byId);
    }

    @PostMapping(value = "updateChapter")
    public Result updateChapter(@RequestBody EduChapter eduChapter){
        eduChapterService.updateById(eduChapter);
        return  Result.Success(null);
    }
    @GetMapping(value = "del/{chapterId}")
    public Result delChapter(@PathVariable String chapterId){
        Boolean ifdel = eduChapterService.delChapterbyId(chapterId);
        if(ifdel){
            return Result.Success(null);
        }{
            return  Result.fail(5001,"该章节下存在小节，请先删除所有小节");
        }
    }
}
