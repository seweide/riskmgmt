package com.haier.hairy.rmp.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 通知状态
 * @author xiehao
 */
@Getter
@AllArgsConstructor
public enum NoticeStatusEnum {
    SUCCESS("S", "成功"),
    FAILURE("F", "失败");

    @EnumValue
    private String code;
    private String name;
}
