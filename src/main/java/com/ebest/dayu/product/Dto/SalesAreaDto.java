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
 * sku销售区域
 * </p>
 *
 * @author heweiwen
 * @since 2020-01-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sales_area")
public class SalesAreaDto extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * pk
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 对象范围类型：1、类目 2、产品 3、sku
     */
    @TableField("object_type")
    private Boolean objectType;

    /**
     * 对象id，根据object_type不同，取不同表的数据
     */
    @TableField("object_id")
    private Long objectId;

    /**
     * 组织id，注意选择该组织后，该组织的儿子到叶子节点均有限制
     */
    @TableField("org_Id")
    private Long orgId;

    /**
     * 创建时间
     */
    @TableField("gmt_create")
    private LocalDateTime gmtCreate;

    /**
     * 更新时间
     */
    @TableField("gmt_modified")
    private LocalDateTime gmtModified;


}
