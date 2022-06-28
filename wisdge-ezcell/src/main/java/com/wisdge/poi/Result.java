package com.wisdge.poi;

import lombok.Data;
import org.apache.poi.ss.usermodel.Workbook;
import java.util.List;

@Data
public class Result<T> {
    private List<T> list;//成功列表
    private int success;//成功条数
    private int fail;//失败条数
    private Workbook errWorkbook;//失败表单
}