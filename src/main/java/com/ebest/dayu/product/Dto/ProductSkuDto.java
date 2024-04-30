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
 * 产品所属sku，如红色+XL，不含价格与库存
 * </p>
 *
 * @author heweiwen
 * @since 2020-01-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("product_sku")
public class ProductSkuDto extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * sku的id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 产品标识
     */
    @TableField("prd_id")
    private Long prdId;

    /**
     * json格式，用来描述sku的定义，如：颜色,红；尺码,XL
     */
    @TableField("sku_content")
    private String skuContent;

    /**
     * sku的图片或视频地址
     */
    @TableField("media_url")
    private String mediaUrl;

    /**
     * sku自定义编码
     */
    @TableField("upc")
    private String upc;

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
     * sku标识,来源于外部系统的编号
     */
    @TableField("sku_no")
    private String skuNo;


}
