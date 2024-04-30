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
 * 万链指数风险预警列表
 * </p>
 *
 * @author Xie hao
 * @since 2019-07-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("wlzs_warning_list")
public class WlzsWarningListEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

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
     * 风险等级编码
     */
    @TableField("level")
    private String level;

    /**
     * 风险等级名称
     */
    @TableField("level_name")
    private String levelName;

    /**
     * 预警日期
     */
    @TableField("alerted_date")
    private String alertedDate;

    /**
     * 预警内容
     */
    @TableField("category")
    private String category;

    /**
     * 预警描述
     */
    @TableField("category_desc")
    private String categoryDesc;


}
