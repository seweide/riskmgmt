package com.haier.hairy.rmp.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 经销商月提货统计
 * </p>
 *
 * @author Xie hao
 * @since 2019-07-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("dts_month_sum")
public class DtsMonthSumEntity extends BaseEntity {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 客户编号，企业编号
     */
    @TableField("khbm")
    private String khbm;

    /**
     * 客户名称，企业名称
     */
    @TableField("khmc")
    private String khmc;

    /**
     * 区域
     */
    @TableField("qy")
    private String qy;

    /**
     * 渠道
     */
    @TableField("qd")
    private String qd;

    /**
     * 日期月份，如201906
     */
    @TableField("rq")
    private String rq;

    /**
     * 合作时长
     */
    @TableField("hzsc")
    private BigDecimal hzsc;

    /**
     * 建户时间
     */
    @TableField("jhsj")
    private Date jhsj;

    /**
     * 门店数量
     */
    @TableField("mdsl")
    private BigDecimal mdsl;

    /**
     * 提货金额
     */
    @TableField("thje")
    private BigDecimal thje;

    /**
     * 提货数量
     */
    @TableField("thsl")
    private BigDecimal thsl;

    /**
     * 提货次数
     */
    @TableField("thcs")
    private BigDecimal thcs;

    /**
     * 履约率
     */
    @TableField("lyl")
    private BigDecimal lyl;

    /**
     * 后返预提
     */
    @TableField("hfyt")
    private BigDecimal hfyt;

    /**
     * 商业政策
     */
    @TableField("syzc")
    private BigDecimal syzc;

    /**
     * 生命周期
     */
    @TableField("smzq")
    private String smzq;

    /**
     * 历史价值
     */
    @TableField("lsjz")
    private String lsjz;

    /**
     * 安全价值
     */
    @TableField("aqjz")
    private String aqjz;

    /**
     * 潜力价值
     */
    @TableField("qljz")
    private String qljz;

    /**
     * 未来价值
     */
    @TableField("wljz")
    private String wljz;

    /**
     * 现汇比
     */
    @TableField("xhb")
    private BigDecimal xhb;


}
