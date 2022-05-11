package com.edu.tx.controller.front;

import com.edu.tx.client.CmsClient;
import com.edu.tx.entity.vo.ChapterVo;
import com.edu.tx.entity.vo.CourseInfoVo;
import com.edu.tx.entity.vo.paremt.Result;
import com.edu.tx.entity.vo.paremt.UserCourseParemt;
import com.edu.tx.service.EduChapterService;
import com.edu.tx.service.EduCourseService;
import com.edu.tx.utils.JwtUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value = "edu/userCourse")
public class UserCourseController {
    @Autowired
    private CmsClient cmsClient;
    @Autowired
    private EduCourseService eduCourseService;
    @Autowired
    private EduChapterService eduChapterService;
    @PostMapping(value="getEduCoursePage/{current}")
    public Result GetEduCoursePage(@RequestBody(required = false) UserCourseParemt paremt,@PathVariable long current){
        HashMap<String, Object> eduCoursePage = eduCourseService.getEduCoursePage(paremt,current);

        return  Result.Success(eduCoursePage);
    }

    @GetMapping(value = "getEduCouresInfo/{courseId}")
    public  Result GetEduCourseInfo(@PathVariable String courseId, HttpServletRequest request){

        CourseInfoVo courseinfo=eduCourseService.getCourseInfo(courseId);
        List<ChapterVo> chapters = eduChapterService.getChapters(courseId);
        HashMap<String, Object> map = new HashMap<>();
        map.put("courseinfo",courseinfo);
        map.put("chapter",chapters);
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        if (StringUtils.isEmpty(memberId)){
            return Result.Success(map);
        }
        Boolean buyCourse = cmsClient.isBuyCourse(memberId, courseId);
        map.put("isBuy",buyCourse);
        return Result.Success(map);



    }
}
