package com.tx.common_utils.utlis.controller;

import com.aliyuncs.exceptions.ClientException;
import com.tx.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "oss")
@CrossOrigin
public class OssController {
        private OssService ossServicel;
        @PostMapping(value = "upload")
        public Result OssUplodate(MultipartFile file){
                String update = ossServicel.update(file);
                return Result.Success(update);
        }

        @PostMapping(value = "uploadVideo")
        public Result UploadVideo(MultipartFile file) throws IOException {
                String videoId = ossServicel.testUploadStream(file);
                return Result.Success(videoId);
        }
        @PostMapping(value = "delVideoList")
        public Result delVideoList(@RequestParam("videoId") List<String> videoId) throws ClientException {
                Boolean flag = ossServicel.delVideo(videoId);
                return Result.Success(flag);
        }
        @GetMapping(value = "delVideo/{videoId}")
        public Result delVideo(@PathVariable String videoId) throws ClientException {
                System.out.println(videoId);
                Boolean flag = ossServicel.delVideo(videoId);
                return Result.Success(flag);
        }
        @GetMapping(value = "getPlay/{id}")
        public Result getPlay(@PathVariable String id) throws ClientException {
                String code= ossServicel.getPlay(id);
                return Result.Success(code);
        }
}
