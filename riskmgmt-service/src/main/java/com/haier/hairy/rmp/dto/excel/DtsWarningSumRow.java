package com.haier.hairy.rmp.dto.excel;

import cn.hutool.core.map.MapUtil;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

/**
 * DTS数据风险预警记录
 * @Author: seweide
 * @Create: 2019-08-13
 **/
@Data
@Builder
@EqualsAndHashCode(callSuper=false)
public class DtsWarningSumRow extends BaseRowModel{

    public static final Map COL_WIDTH_MAP;

    static{
        COL_WIDTH_MAP = MapUtil.of(new Integer[][] {
                {0, 10000},
                {1, 4000},
                {2, 4000},
                {3, 4000},
                {4, 4000},
                {5, 20000}
        });
    }

    @ExcelProperty(value = "企业名称", index = 0)
    private String corpName;

    @ExcelProperty(value = "命中规则1", index = 1)
    private String hitRules1;

    @ExcelProperty(value = "命中规则2", index = 2)
    private String hitRules2;

    @ExcelProperty(value = "命中规则3", index = 3)
    private String hitRules3;

    @ExcelProperty(value = "授信状态", index = 4)
    private String creditApplyStatus;

    @ExcelProperty(value = "贷款订单", index = 5)
    private String loanListStr;
}
