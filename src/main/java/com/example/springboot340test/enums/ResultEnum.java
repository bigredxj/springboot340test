package com.example.springboot340test.enums;

/** 
 * <p>异常枚举类</p>
 * @author
 * code定义方式：
 * 1.自定义功能的code从100001开始。
 * 2.每添加一个功能分组对code进行百位加一，个位清零的操作。例如A组的最大code是100911，则新增B组的起始code是101001
 */
public enum ResultEnum {

	/**
	 * 操作失败
	 */
	OPERATION_FAILED(-1, "操作失败"),

	SYSTEM_ERROR(2, "系统错误,请稍后重试或联系管理员!"),
    LOGIN_INFO_NULL(4, "用户名为空!"),
    VALIDATE_FAILED(5, "验证失败,密码错误!"),
    PASSWORD_RESET_FAILED(6, "密码重置失败"),
	SUCCESS(200, "操作成功"),
    OPERATION_SUCCESS_WITH_WARN(201, "操作成功但存在告警信息"),
    BAD_REQ_PARAM(402,"非法请求，参数无效"),
	NOT_LOGIN(301, "您还未登录，请登录后重试!"),
	DATA_EXIST(601,"提交的数据已存在"),
	DATA_NOT_EXIST(602,"请求的数据不存在"),
    DATA_ADD_FAILED(602,"提交的数据写入失败"),

	//用户登录
	LOGIN_FAILED(100001, "用户名或密码错误!"),
	LOGIN_OUT_FAILED(100002, "退出失败!"),

	//mysql
	INSERT_FAILED(200001, "mysql insert failed"),

	//license
	UPLOAD_LICENSE_ERROR(110001,"license上传失败"),
	CHECK_LICENSE_ERROR(110002,"license不正确")
    ;

    /** 错误码 */
    private Integer code;
    /** 错误信息 */
    private String message;
	ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
	/**
	 * @return the code
	 */
	public Integer getCode() {
		return code;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}  
}
