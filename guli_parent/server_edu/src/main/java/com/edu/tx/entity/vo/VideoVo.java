package com.edu.tx.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class VideoVo {
    private String id;
    private String title;
    private String chapterId;
    private Integer isFree;
    @ApiModelProperty(value = "云端视频资源")
    private String videoSourceId;
    private String videoOriginalName;
}
