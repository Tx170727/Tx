package com.tx.common_utils.utlis;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
@Component
public class UploadVideo {
    public String testUploadStream(MultipartFile file) throws IOException {
        UploadStreamRequest request = new UploadStreamRequest("LTAI5t91ZcSCRuVcwdZ2eUPE", "zxkhK78QkuHMtmbIsJXIzfX9S5zRD5",
                file.getOriginalFilename(), file.getOriginalFilename(), file.getInputStream());
        UploadVideoImpl uploader = new UploadVideoImpl();
        UploadStreamResponse response = uploader.uploadStream(request);
        return response.getVideoId();
        }

    }

