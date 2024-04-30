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
 * 万链指数风险预警详情
 * </p>
 *
 * @author Xie hao
 * @since 2019-07-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("wlzs_warning_detail")
public class WlzsWarningDetailEntity extends BaseEntity {

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
     * 预警大类编码
     */
    @TableField("category_code")
    private String categoryCode;

    /**
     * 预警大类名称
     */
    @TableField("category_name")
    private String categoryName;

    /**
     * 预警大类编码
     */
    @TableField("type_code")
    private String typeCode;

    /**
     * 预警大类名称
     */
    @TableField("type_name")
    private String typeName;

    /**
     * 预警风险等级编码
     */
    @TableField("alerted_level")
    private String alertedLevel;

    /**
     * 预警日期
     */
    @TableField("alerted_date")
    private String alertedDate;

    /**
     * 预警内容
     */
    @TableField("alerted_content")
    private String alertedContent;

    /**
     * 执行人ID
     */
    @TableField("person_executed_id")
    private String personExecutedId;

    /**
     * 预警是否可更新
     */
    @TableField("update_flag")
    private String updateFlag;


}
