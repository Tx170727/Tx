package com.tx.server_edu;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes=test.class)
public class test {
    @Test
    void contextLoads() {
    }
    @Test
    public void test(){
        String load="d:/test.xlsx";
//        List<excelData> excels= new ArrayList<>();
//        excelData excelData = new excelData();
//        excelData.setCode(1);
//        excelData.setName("tx");
//        excels.add(excelData);
//        EasyExcel.write(load, excelData.class).sheet("第一页").doWrite(excels);

//        EasyExcel.read(load, excelData.class,new ExcelListener()).sheet().doRead();
    }


}
