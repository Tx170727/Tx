package com.tx.common_utils.utlis.service.impl;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.DeleteVideoResponse;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.tx.common_utils.utlis.ClientUtils;
import com.tx.common_utils.utlis.controller.OssService;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

@Service
public class OssServiceImpl implements OssService {
    // Endpoint以华东1（杭州）为例，其它Region请按实际情况填写。
    @Value("${aliyun.oss.file.endpoint}")
    private String endpoint;
    // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
    @Value("${aliyun.oss.file.accessKeyId}")
    String accessKeyId;
    @Value("${aliyun.oss.file.secretAccessKey}")
    String accessKeySecret;
    // 填写Bucket名称，例如examplebucket。
    @Value("${aliyun.oss.file.bucketname}")
    String bucketName;
    // 填写Object完整路径，例如exampledir/exampleobject.txt。Object完整路径中不能包含Bucket名称。
    public String update(MultipartFile file) {
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        try {
            InputStream inputStream =  file.getInputStream();
            String objectName= UUID.randomUUID()+file.getOriginalFilename();
            String timepath =  new DateTime().toString("yyyy/MM/dd");
            objectName=timepath+"/"+objectName;
            ossClient.putObject(bucketName, objectName, inputStream);
            return  "https://"+bucketName+"."+endpoint+"/"+objectName;
        } catch (
                OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
        } catch (
                ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
        return null;
    }
    public String testUploadStream(MultipartFile file) throws IOException {
        UploadStreamRequest request = new UploadStreamRequest("LTAI5t91ZcSCRuVcwdZ2eUPE", "zxkhK78QkuHMtmbIsJXIzfX9S5zRD5",
                file.getOriginalFilename(), file.getOriginalFilename(), file.getInputStream());
        UploadVideoImpl uploader = new UploadVideoImpl();
        UploadStreamResponse response = uploader.uploadStream(request);
        return response.getVideoId();
    }

    @Override
    public Boolean delVideo(String videoId) throws com.aliyuncs.exceptions.ClientException {
        DefaultAcsClient client = ClientUtils.initVodClient();
        DeleteVideoResponse response = new DeleteVideoResponse();
        try {
            DeleteVideoRequest request = new DeleteVideoRequest();
            //支持传入多个视频ID，多个用逗号分隔
            request.setVideoIds(videoId);
            response = client.getAcsResponse(request);
        } catch (Exception e) {
           throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public String getPlay(String id) throws com.aliyuncs.exceptions.ClientException {
        DefaultAcsClient client = ClientUtils.initVodClient();
        GetVideoPlayAuthResponse response = new GetVideoPlayAuthResponse();
        try {
            GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
            request.setVideoId(id);
            response = client.getAcsResponse(request);

            //播放凭证
            System.out.print("PlayAuth = " + response.getPlayAuth() + "\n");
            return response.getPlayAuth();
        } catch (Exception e) {
            System.out.print("ErrorMessage = " + e.getLocalizedMessage());
        }
       return "";

    }

    @Override
    public Boolean delVideo(List<String> videoIds) throws com.aliyuncs.exceptions.ClientException {
        DefaultAcsClient client = ClientUtils.initVodClient();
        DeleteVideoResponse response = new DeleteVideoResponse();
        try {
            DeleteVideoRequest request = new DeleteVideoRequest();
            //支持传入多个视频ID，多个用逗号分隔
            StringBuilder strBuilder=new StringBuilder();
            String str=",";
            for(int i = 1; i<=videoIds.size(); i++){
                    strBuilder.append(videoIds.get(i-1)).append(str);
            }
            String s1 = StringUtils.join(videoIds, ",");
            String s = strBuilder.toString();
            request.setVideoIds(s);
            response = client.getAcsResponse(request);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return null;
    }
}
