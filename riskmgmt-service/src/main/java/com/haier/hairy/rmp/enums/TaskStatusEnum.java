package com.haier.hairy.rmp.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 任务执行状态
 * @author xiehao
 */
@Getter
@AllArgsConstructor
public enum TaskStatusEnum {
    INITIAL("I", "初始"),
    PROCESSING("P", "处理中"),
    SUCCESS("S", "成功"),
    FAILURE("F", "失败");

    @EnumValue
    private String code;
    private String name;
}
