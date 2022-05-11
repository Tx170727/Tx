package com.edu.tx.entity.vo.paremt;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class ExcelParemt {
    @ExcelProperty(index = 0)
    private String oneSubjectname;
    @ExcelProperty(index = 1)
    private String twoSubjectname;
}
