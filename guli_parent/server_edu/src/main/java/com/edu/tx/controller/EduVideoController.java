package com.edu.tx.controller;


import com.aliyuncs.exceptions.ClientException;
import com.edu.tx.entity.EduVideo;
import com.edu.tx.entity.vo.paremt.Result;
import com.edu.tx.service.EduVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-03-24
 */
@RestController
@RequestMapping("/edu/video")
public class EduVideoController {

    @Autowired
    private EduVideoService videosService;
    @PostMapping(value = "saveVideo")
    public Result saveVideo(@RequestBody EduVideo eduVideo){
        System.out.println(eduVideo);
        videosService.save(eduVideo);
        return  Result.Success(null);
    }

    @GetMapping(value = "del/{videoId}")
    public Result delVideo(@PathVariable String videoId) throws ClientException {
        Integer b = videosService.delById(videoId);
        return Result.Success(b);
    }
    @GetMapping(value = "get/{videoId}")
    public Result getVideoById(@PathVariable String videoId){
        return Result.Success(videosService.getById(videoId));
    }

    @PostMapping(value = "updateVideo")
    public Result updateVideo(@RequestBody EduVideo eduVideo){
        boolean b = videosService.updateById(eduVideo);
        return Result.Success(b);
    }
}

