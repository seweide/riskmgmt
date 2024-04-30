package com.ebest.dayu.product.Dto;

import com.haier.hairy.rmp.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 产品单位关系表
 * </p>
 *
 * @author heweiwen
 * @since 2020-01-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("prdunit_relationship")
public class PrdunitRelationshipDto extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 单位id
     */
    @TableId("unit_id")
    private Long unitId;

    /**
     * 产品id
     */
    @TableField("prd_id")
    private Long prdId;

    /**
     * 创建时间
     */
    @TableField("gmt_create")
    private LocalDateTime gmtCreate;

    /**
     * 修改时间
     */
    @TableField("gmt_modified")
    private LocalDateTime gmtModified;

    /**
     * 排序位置，数字越大越靠前，从1开始
     */
    @TableField("order_seq")
    private Long orderSeq;


}
