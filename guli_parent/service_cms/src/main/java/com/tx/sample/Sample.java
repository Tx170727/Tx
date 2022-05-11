// This file is auto-generated, don't edit it. Thanks.
package com.tx.sample;

import com.alibaba.fastjson.JSON;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.aliyun.tea.TeaModel;
import com.aliyun.teaopenapi.models.Config;

public class Sample {

    /**
     * 使用AK&SK初始化账号Client
     * @param accessKeyId
     * @param accessKeySecret
     * @return Client
     * @throws Exception
     */
    public static com.aliyun.dysmsapi20170525.Client createClient(String accessKeyId, String accessKeySecret) throws Exception {
        Config config = new Config()
                // 您的AccessKey ID
                .setAccessKeyId(accessKeyId)
                // 您的AccessKey Secret
                .setAccessKeySecret(accessKeySecret);
        // 访问的域名
        config.endpoint = "dysmsapi.aliyuncs.com";
        return new com.aliyun.dysmsapi20170525.Client(config);
    }

    public static String getAliyunCode(String PhoneNumber) throws Exception {
        com.aliyun.dysmsapi20170525.Client client = Sample.createClient("LTAI5t91ZcSCRuVcwdZ2eUPE", "zxkhK78QkuHMtmbIsJXIzfX9S5zRD5");
        StringBuffer stringBuffer = new StringBuffer();

        String code=randomNum.GetRandom();
        stringBuffer.append("{")
                .append(JSON.toJSONString("code")   )
                .append(":")
                .append(JSON.toJSONString(code))
                .append("}");
        SendSmsRequest sendSmsRequest = new SendSmsRequest()
                .setSignName("阿里云短信测试")
                .setTemplateCode("SMS_154950909")
                .setPhoneNumbers(PhoneNumber)
                .setTemplateParam(stringBuffer.toString());
        // 复制代码运行请自行打印 API 的返回值
        SendSmsResponse resp = client.sendSms(sendSmsRequest);
        com.aliyun.teaconsole.Client.log(com.aliyun.teautil.Common.toJSONString(TeaModel.buildMap(resp)));
        return code;
    }
}
