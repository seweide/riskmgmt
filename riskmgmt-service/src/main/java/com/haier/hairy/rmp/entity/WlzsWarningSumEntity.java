package com.haier.hairy.rmp.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 万链指数风险预警汇总表
 * </p>
 *
 * @author Xie hao
 * @since 2019-07-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("wlzs_warning_sum")
public class WlzsWarningSumEntity extends BaseEntity {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 报告日期
     */
    @TableField("report_date")
    private Date reportDate;

    /**
     * 企业名称
     */
    @TableField("corp_name")
    private String corpName;

    /**
     * 高级风险数量
     */
    @TableField("hight_risk_cnt")
    private Integer hightRiskCnt;

    /**
     * 中级风险数量
     */
    @TableField("middle_risk_cnt")
    private Integer middleRiskCnt;

    /**
     * 低级风险数量
     */
    @TableField("low_risk_cnt")
    private Integer lowRiskCnt;


}
