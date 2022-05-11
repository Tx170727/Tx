package com.edu.tx.entity.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SureCourseVo {
    private String id;
    private String title;
    private BigDecimal price;
    private Integer lessonNum;
    private String teacherName;
    private String subjectParent;
    private String subject;
    private String cover;
}
