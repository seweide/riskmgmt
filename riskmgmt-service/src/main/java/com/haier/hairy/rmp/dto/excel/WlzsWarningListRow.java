package com.haier.hairy.rmp.dto.excel;

import cn.hutool.core.map.MapUtil;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

/**
 * 万链指数风险预警列表页记录
 *
 * @Author: Xie Hao
 * @Create: 2019-07-29 17:17
 **/
@Data
@EqualsAndHashCode(callSuper=false)
public class WlzsWarningListRow extends BaseRowModel {

    public static final Map COL_WIDTH_MAP;

    static{
        COL_WIDTH_MAP = MapUtil.of(new Integer[][] {
                {0, 10000},
                {1, 5000},
                {2, 5000},
                {3, 4000},
                {4, 20000},
                {5, 20000}
        });
    }

    @ExcelProperty(value = "企业名称", index = 0)
    private String corpName;

    @ExcelProperty(value = "风险等级编码", index = 1)
    private String level;

    @ExcelProperty(value = "风险等级名称", index = 2)
    private String levelName;

    @ExcelProperty(value = "预警日期", index = 3)
    private String alertedDate;

    @ExcelProperty(value = "预警内容", index = 4)
    private String category;

    @ExcelProperty(value = "预警描述", index = 5)
    private String categoryDesc;

}
