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
 * 类目表
 * </p>
 *
 * @author heweiwen
 * @since 2020-01-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("category_cfg")
public class CategoryCfgDto extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 类目业务标识
     */
    @TableField("cate_no")
    private String cateNo;

    /**
     * 类目名称
     */
    @TableField("name")
    private String name;

    /**
     * 0、代表该类目不是品牌  1、代表该类目是品牌
     */
    @TableField("is_brand")
    private Boolean isBrand;

    /**
     * 品牌logo的地址
     */
    @TableField("logo_url")
    private String logoUrl;

    /**
     * 记录创建时间，使用格林尼标准时间
     */
    @TableField("gmt_create")
    private LocalDateTime gmtCreate;

    /**
     * 记录修改时间，使用格林尼标准时间
     */
    @TableField("gmt_modified")
    private LocalDateTime gmtModified;

    /**
     * 类目定义来源：1、系统定义类目 2、品牌方自定义
     */
    @TableField("owner_from")
    private Boolean ownerFrom;

    /**
     * 类目创建者id
     */
    @TableField("owner_id")
    private Long ownerId;

    /**
     * 英文名
     */
    @TableField("english_name")
    private String englishName;

    /**
     * 类目级别
     */
    @TableField("cate_level")
    private Boolean cateLevel;


}
