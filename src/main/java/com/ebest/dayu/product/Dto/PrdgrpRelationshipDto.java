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
 * 产品与分组关系，一个产品可以在多个分组中，一个分组可以包含多个产品
 * </p>
 *
 * @author heweiwen
 * @since 2020-01-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("prdgrp_relationship")
public class PrdgrpRelationshipDto extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 产品id
     */
    @TableId("prd_id")
    private Long prdId;

    /**
     * 分组id
     */
    @TableField("grp_id")
    private Long grpId;

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
