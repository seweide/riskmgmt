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
 * 产品上下架日志表
 * </p>
 *
 * @author heweiwen
 * @since 2020-01-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("product_onshelf_log")
public class ProductOnshelfLogDto extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * pk
     */
    @TableId("id")
    private Long id;

    /**
     * user表pk
     */
    @TableField("user_id")
    private Long userId;

    @TableField("gmt_create")
    private LocalDateTime gmtCreate;

    /**
     * product_metadata表pk
     */
    @TableField("product_id")
    private Long productId;

    /**
     * 上下架状态 0、下架  1、上架
     */
    @TableField("onshelf")
    private Boolean onshelf;


}
