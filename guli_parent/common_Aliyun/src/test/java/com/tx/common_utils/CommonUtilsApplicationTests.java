package com.tx.common_utils;

import com.aliyuncs.exceptions.ClientException;
import com.tx.common_utils.utlis.UploadOss;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
@SpringBootTest
class CommonUtilsApplicationTests {
    @Autowired
    private UploadOss uploadOss;
    @Test
    void contextLoads() {
    }
    @Test
    public void test() throws ClientException {

    }
}
