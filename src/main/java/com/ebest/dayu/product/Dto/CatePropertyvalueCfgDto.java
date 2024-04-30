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
 * 类目属性所对应的选型值，品牌具体名称
 * </p>
 *
 * @author heweiwen
 * @since 2020-01-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("cate_propertyvalue_cfg")
public class CatePropertyvalueCfgDto extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 类目属性值id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 对应cate_property_cfg的pk_id,
     */
    @TableField("procfg_id")
    private Long procfgId;

    /**
     * 属性值
     */
    @TableField("value")
    private String value;

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

    /**
     * 属性拥有者id
     */
    @TableField("owner_id")
    private Long ownerId;


}
