package com.haier.hairy.rmp.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: Xie Hao
 * @Create: 2019-07-22 15:40
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class BaseEntity implements Serializable {
    private static final long serialVersionUID=1L;

    /**
     * 乐观锁版本
     */
    @TableField("jpa_version")
    @Version
    private Integer jpaVersion;

    /**
     * 是否删除 0=否，1=是
     */
    @TableField("deleted")
    @TableLogic
    private String deleted = "0";

    /**
     * 备注
     */
    @TableField("remark")
    private String remark;

    /**
     * 创建时间
     */
    @TableField("created_time")
    private Date createdTime;

    /**
     * 更新时间
     */
    @TableField("updated_time")
    private Date updatedTime;
}
