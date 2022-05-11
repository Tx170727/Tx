package com.tx.entity.Vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CourseInfoVo {

    private String id;
    private String title;
    private BigDecimal price;
    private String cover;
    private Integer buyCount;
    private Integer viewCount;
    private String teacherId;
    private String teahcerName;
    private String description;
    private String intro;
    private String avatar;
    private Integer lessonNum;
    private String subjectLevelOneId;
    private String subjectLevelOne;
    private String subjectLevelTwoId;
    private String subjectLevelTwo;
}
