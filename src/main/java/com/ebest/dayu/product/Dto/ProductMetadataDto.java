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
 * 产品主表
 * </p>
 *
 * @author heweiwen
 * @since 2020-01-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("product_metadata")
public class ProductMetadataDto extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * pk
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 外部产品编号
     */
    @TableField("product_no")
    private byte[] productNo;

    /**
     * 排序号
     */
    @TableField("sort_no")
    private byte[] sortNo;

    /**
     * 商品标签 (数组多值)0:新品 ,1:重点 ,2:赠品,3:促销,4:热卖
     */
    @TableField("stort_label")
    private String stortLabel;

    /**
     * 产品名称
     */
    @TableField("name")
    private String name;

    /**
     * 产品主图列表,json数组格式
     */
    @TableField("main_img")
    private String mainImg;

    @TableField("short_video")
    private String shortVideo;

    /**
     * 产品简述，纯文字
     */
    @TableField("short_description")
    private String shortDescription;

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
     * 本地名称
     */
    @TableField("local_name")
    private String localName;

    /**
     * 简称
     */
    @TableField("short_name")
    private String shortName;

    /**
     * 助记码
     */
    @TableField("short_mnemonim")
    private String shortMnemonim;

    /**
     * 常用单位批发价
     */
    @TableField("price")
    private byte[] price;

    /**
     * 建议零售价
     */
    @TableField("retail_price")
    private byte[] retailPrice;


}
