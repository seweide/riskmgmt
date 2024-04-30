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
 * 类目属性表，用于定义一个类目下所拥有的属性：
如生产批次、品牌、材质、包装
 * </p>
 *
 * @author heweiwen
 * @since 2020-01-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("cate_property_cfg")
public class CatePropertyCfgDto extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * pk
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 业务标识，唯一，外部系统编号
     */
    @TableField("property_no")
    private String propertyNo;

    /**
     * 属性名
     */
    @TableField("name")
    private String name;

    /**
     * 属性来源：属性定义来源：1、代表类目 2、代表产品
     */
    @TableField("owner_from")
    private Boolean ownerFrom;

    /**
     * 属性拥有者id
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


}
