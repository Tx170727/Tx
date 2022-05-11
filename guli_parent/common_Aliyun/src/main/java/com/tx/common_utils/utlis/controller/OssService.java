package com.tx.common_utils.utlis.controller;

import com.aliyuncs.exceptions.ClientException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface OssService {
    public String update(MultipartFile file);

    public String testUploadStream(MultipartFile file) throws IOException;

    Boolean delVideo(List<String> videoId) throws ClientException;

    Boolean delVideo(String videoId) throws ClientException;

    String getPlay(String id) throws ClientException;
}
