package com.ebest.dayu.product.Dto;

import com.haier.hairy.rmp.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * sku分类(即维度)的定义
 * </p>
 *
 * @author heweiwen
 * @since 2020-01-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("skucls_cfg")
public class SkuclsCfgDto extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 外部系统sku分类标识
     */
    @TableField("skucls_no")
    private String skuclsNo;

    /**
     * sku分类名称
     */
    @TableField("name")
    private String name;

    /**
     * sku维度定义来源：1、代表类目 2、代表产品
     */
    @TableField("owner_from")
    private Boolean ownerFrom;

    @TableField("gmt_create")
    private LocalDateTime gmtCreate;

    @TableField("gmt_modified")
    private LocalDateTime gmtModified;

    /**
     * 属性拥有者id
     */
    @TableField("owner_id")
    private Long ownerId;


}
