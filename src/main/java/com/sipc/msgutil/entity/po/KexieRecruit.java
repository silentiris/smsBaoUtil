package com.sipc.msgutil.entity.po;

import com.alibaba.excel.annotation.ExcelProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class KexieRecruit {
    @ExcelProperty("name")
    private String name;
    @ExcelProperty("phone")
    private String phone;
    @ExcelProperty("testPlace")
    private String testPlace;
    @ExcelProperty("testPos")
    private Integer testPos;
    @ExcelProperty("nextTime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String interviewTime;
    @ExcelProperty("nextPlace")
    private String interviewPlace;
}
