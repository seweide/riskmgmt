package com.haier.hairy.rmp.dto.excel;

import cn.hutool.core.map.MapUtil;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashMap;
import java.util.Map;

/**
 * 万链指数风险预警汇总页记录
 * @Author: Xie Hao
 * @Create: 2019-07-29 15:57
 **/
@Data
@Builder
@EqualsAndHashCode(callSuper=false)
public class WlzsWarningSumRow extends BaseRowModel{

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

    @ExcelProperty(value = "高级风险数", index = 1)
    private int hightRiskCnt;

    @ExcelProperty(value = "中级风险数", index = 2)
    private int middleRiskCnt;

    @ExcelProperty(value = "低级风险数", index = 3)
    private int lowRiskCnt;

    @ExcelProperty(value = "授信状态", index = 4)
    private String creditApplyStatus;

    @ExcelProperty(value = "贷款订单", index = 5)
    private String loanListStr;
}
