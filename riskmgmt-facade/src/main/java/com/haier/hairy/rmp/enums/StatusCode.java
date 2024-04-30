package com.haier.hairy.rmp.enums;

/**
 * 功能： 状态码。定义状态码及提示信息
 *
 * @author DH.L
 * @date 2018/06/06
 */
public enum StatusCode {

    SUCCESS(0, "正常处理"),
    PARAMETER_ERROR(1, "参数错误"),
    LOGICAL_ERROR(2, "逻辑错误"),
    SAVE_ERROR(3, "保存失败"),
    NO_IMPLEMENT(4, "方法未实现"),
    EXIST_RECORD(5, "记录已经存在"),

    NOT_FOUND(1000, "查询记录不存在！"),
    CHECK_ERROR(1001, "校验不满足条件"),
    SCENARIO_NOT_EXIST(1002, "场景不存在"),
    SCENARIO_NO_IS_EMPTY(1003, "场景编号为空"),
    SCENARIO_PARAMS_IS_EMPTY(1004, "场景参数为空"),
    SCENARIO_PARAMS_FORMAT_ERROR(1005, "场景参数格式异常"),
    SCENARIO_PARAMS_FIELD_EMPTY(1006, "场景参数必填字段不能为空"),
    INTERACTION_NO_IS_EMPTY(1003, "节点编号有误或为空"),

    APP_NO_IS_EMPTY(2001,"APP_NO为空"),
    BLAZE_REQUEST_XML_IS_EMPTY(2002,"Blaze请求XML为空"),
    BLAZE_APP_SERIAL_CASH_IS_EMPTY(2003,"流水号为空"),
    BLAZE_NODE_TYPE_IS_EMPTY(2004,"节点类型为空"),

    DB_ERROR(9001, "数据库错误！"),
    PARSE_DATE_ERROR(9002, "时间转换出错！"),
    INVOKE_INTERFACE_ERROR(9993, "调用接口出现异常！"),
    SYSTEM_MAINTAIN(9990, "第三方系统维护中！"),
    UNKNOWN_BIZ_ERROR(9998, "未知的业务异常！"),
    SYSTEM_ERROR(9999, "系统错误"),
    NETWORK_ERROR(9998, "网络异常");

    private int status;

    private String message;

    StatusCode(int status, String message) {
        this.status = status;
        this.message = message;
    }

    /**
     * 通过代码获取枚举项
     */
    public static StatusCode getByCode(Integer statusCode) {
        if (statusCode == null) {
            return UNKNOWN_BIZ_ERROR;
        }

        for (StatusCode at : values()) {
            if (at.getStatus() == statusCode) {
                return at;
            }
        }

        return UNKNOWN_BIZ_ERROR;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}