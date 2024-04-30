package com.haier.hairy.rmp.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * <p>
 * 任务记录表
 * </p>
 *
 * @author Xie hao
 * @since 2019-07-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("task_record")
public class TaskRecordEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 任务日期
     */
    @TableField("report_date")
    private Date reportDate;

    /**
     * 任务类型 1=万链指数预警，2=DTS预警
     */
    @TableField("report_type")
    private Integer reportType;

    /**
     * 任务开始时间
     */
    @TableField("start_time")
    private Date startTime;

    /**
     * 任务结束时间
     */
    @TableField("end_time")
    private Date endTime;

    /**
     * 任务状态 I=初始化， P=处理中，S=成功，F=失败
     */
    @TableField("task_status")
    private String taskStatus;

    /**
     * 通知时间
     */
    @TableField("notice_time")
    private Date noticeTime;

    /**
     * 通知结果 S=成功，F=失败
     */
    @TableField("notice_status")
    private String noticeStatus;


}
