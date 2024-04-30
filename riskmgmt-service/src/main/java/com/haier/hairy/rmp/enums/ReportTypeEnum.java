package com.haier.hairy.rmp.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * 预警报告类型
 *
 * @author xiehao
 */
@Getter
@AllArgsConstructor
public enum ReportTypeEnum {
    WLZS_WARNING(1, "万链指数预警"),
    DTS_WARNING(2, "DTS数据预警");

    @EnumValue
    private int code;
    private String name;

    private static Map<Integer, ReportTypeEnum> map = new HashMap<>();

    static {
       for(ReportTypeEnum enumObj : values()){
           map.put(enumObj.code, enumObj);
       }
    }

    public static boolean isExist(Integer code){
        return code == null ? false : map.containsKey(code);
    }

    public boolean isEquals(Integer code){
        return code == null ? false : this.code == code;
    }

}
