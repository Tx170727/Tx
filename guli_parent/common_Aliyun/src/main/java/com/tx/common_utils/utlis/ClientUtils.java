package com.tx.common_utils.utlis;

import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;

public class ClientUtils {

    //填入AccessKey信息
    public static DefaultAcsClient initVodClient() throws ClientException {
        String regionId = "cn-shanghai";  // 点播服务接入地域
        DefaultProfile profile = DefaultProfile.getProfile(regionId, "LTAI5t91ZcSCRuVcwdZ2eUPE", "zxkhK78QkuHMtmbIsJXIzfX9S5zRD5");
        DefaultAcsClient client = new DefaultAcsClient(profile);
        return client;
    }
}
