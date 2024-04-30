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
 * 类目关系表，该结构为森林，一个类目可以有0到多个父亲或0到多个儿子
 * </p>
 *
 * @author heweiwen
 * @since 2020-01-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("cate_relationship_cfg")
public class CateRelationshipCfgDto extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 类目标识
     */
    @TableId("category_id")
    private Long categoryId;

    /**
     * 父类目标识
     */
    @TableField("parent_id")
    private Long parentId;

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
     * 同一个类目下的排序,数字越大越靠前，初始为0
     */
    @TableField("order_seq")
    private Long orderSeq;


}
