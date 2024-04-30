package com.haier.hairy.rmp.dto.excel;

import cn.hutool.core.map.MapUtil;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

/**
 * 万链指数风险预警详情页记录
 *
 * @Author: Xie Hao
 * @Create: 2019-07-29 17:17
 **/
@Data
@EqualsAndHashCode(callSuper=false)
public class WlzsWarningDetailRow extends BaseRowModel {

    public static final Map COL_WIDTH_MAP;

    static{
        COL_WIDTH_MAP = MapUtil.of(new Integer[][] {
                {0, 10000},
                {1, 4000},
                {2, 6000},
                {3, 4000},
                {4, 6000},
                {5, 4000},
                {6, 4000},
                {7, 25000},
                {8, 2000},
                {9, 2000}
        });
    }

    @ExcelProperty(value = "企业名称", index = 0)
    private String corpName;

    @ExcelProperty(value = "大类编码", index = 1)
    private String categoryCode;

    @ExcelProperty(value = "大类名称", index = 2)
    private String categoryName;

    @ExcelProperty(value = "小类编码", index = 3)
    private String typeCode;

    @ExcelProperty(value = "小类名称", index = 4)
    private String typeName;

    @ExcelProperty(value = "风险等级", index = 5)
    private String alertedLevel;

    @ExcelProperty(value = "预警日期", index = 6)
    private String alertedDate;

    @ExcelProperty(value = "预警内容", index = 7)
    private String alertedContent;

    @ExcelProperty(value = "执行人ID", index = 8)
    private String personExecutedId;

    @ExcelProperty(value = "可更新", index = 9)
    private String updateFlag;
}
