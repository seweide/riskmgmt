package com.haier.hairy.rmp.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * <p>
 * DTS指数风险预警汇总表
 * </p>
 *
 * @author Seweide
 * @since 2019-08-15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("dts_warning_sum")
public class DtsWarningSumEntity extends BaseEntity {

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
     * 命中规则1
     */
    @TableField("hit_rules_1")
    private String hitRules1;

    /**
     * 命中规则2
     */
    @TableField("hit_rules_2")
    private String hitRules2;

    /**
     * 命中规则3
     */
    @TableField("hit_rules_3")
    private String hitRules3;


}
