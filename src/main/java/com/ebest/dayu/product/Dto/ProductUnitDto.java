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
 * 产品单位表
 * </p>
 *
 * @author heweiwen
 * @since 2020-01-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("product_unit")
public class ProductUnitDto extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * pk
     */
    @TableId("id")
    private Long id;

    /**
     * product_metadata表pk
     */
    @TableField("product_id")
    private Long productId;

    /**
     * 常用单位（0：小单位，1：大单位）
     */
    @TableField("unit_type")
    private Long unitType;

    /**
     * 条形码
     */
    @TableField("bar_code")
    private String barCode;

    /**
     * 换算比，小单位换算大单位比值
     */
    @TableField("conversion_ratio")
    private Long conversionRatio;

    /**
     * 重量单位
     */
    @TableField("weitght")
    private Long weitght;

    /**
     * 长（cm）
     */
    @TableField("longs")
    private Long longs;

    /**
     * 宽（cm）
     */
    @TableField("wide")
    private Long wide;

    /**
     * 高（cm）
     */
    @TableField("high")
    private Long high;

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
     * 属性对应的json
     */
    @TableField("property_content")
    private String propertyContent;

    /**
     * 上下架状态 0、下架  1、上架
     */
    @TableField("onshelf")
    private Boolean onshelf;


}
