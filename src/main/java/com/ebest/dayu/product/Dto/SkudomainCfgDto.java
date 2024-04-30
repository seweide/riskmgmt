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
 * sku值域
 * </p>
 *
 * @author heweiwen
 * @since 2020-01-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("skudomain_cfg")
public class SkudomainCfgDto extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * sku分类值id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * FK ，skucls_cfg表的PK
     */
    @TableField("skucls_cfg_id")
    private Long skuclsCfgId;

    /**
     * 外部sku分类值id
     */
    @TableField("skuval_no")
    private String skuvalNo;

    /**
     * sku分类值
     */
    @TableField("value")
    private String value;

    /**
     * sku创建者id
     */
    @TableField("owner_id")
    private Long ownerId;

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
     * 类目定义来源：1、系统定义类目 2、品牌方自定义
     */
    @TableField("owner_from")
    private Boolean ownerFrom;


}
