package com.tx.common_utils.utlis;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Component
public class UploadOss {

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
    }


