package com.wisdge.web;

import lombok.Data;

/**
 *  <p> API返回参数 </p>
 */
@Data
public class ApiResult<T> {
    /**
     * 消息内容
     */
    private String message;

    /**
     * 响应码：参考`ResultCode`
     */
    private Integer code;

    /**
     * 响应中的数据
     */
    private T data;

    /***
     * 返回错误码400
     *
     * @param message String 错误消息内容
     * @return ApiResult
     */
    public static ApiResult fail(String message) {
        return new ApiResult(ResultCode.BAD_REQUEST.getCode(), message, null);
    }

    /***
     * 自定义错误返回码
     *
     * @param code int 状态码
     * @param message String 错误消息内容
     * @return ApiResult
     */
    public static ApiResult fail(Integer code, String message) {
        return new ApiResult(code, message, null);
    }

    /***
     * 返回错误码404
     *
     * @return: ApiResult
     */
    public static ApiResult notFound() {
        return new ApiResult(ResultCode.NOT_FOUND.getCode(), ResultCode.NOT_FOUND.getDesc(), null);
    }

    /***
     * 返回错误码404
     * @param message String
     * @return ApiResult
     */
    public static ApiResult notFound(String message) {
        return new ApiResult(ResultCode.NOT_FOUND.getCode(), message, null);
    }

    /***
     * 返回错误码403
     *
     * @return ApiResult
     */
    public static ApiResult forbidden() {
        return new ApiResult(ResultCode.FORBIDDEN.getCode(), ResultCode.FORBIDDEN.getDesc(), null);
    }

    /***
     * 返回错误码403
     * @param message String
     * @return ApiResult
     */
    public static ApiResult forbidden(String message) {
        return new ApiResult(ResultCode.FORBIDDEN.getCode(), message, null);
    }

    /***
     * 返回错误码401
     *
     * @param message String
     * @return ApiResult
     */
    public static ApiResult unauthorized(String message) {
        return new ApiResult(ResultCode.UNAUTHORIZED.getCode(), message, null);
    }

    /***
     * 返回错误码401
     *
     * @param result ResultCode
     * @return ApiResult
     */
    public static ApiResult unauthorized(ResultCode result) {
        return new ApiResult(ResultCode.UNAUTHORIZED.getCode(), result.getDesc(), result.getCode());
    }

    /***
     * 返回错误码401
     *
     * @return ApiResult
     */
    public static ApiResult unauthorized() {
        return new ApiResult(ResultCode.UNAUTHORIZED.getCode(), ResultCode.UNAUTHORIZED.getDesc(), null);
    }

    /***
     * 返回错误码500
     *
     * @return ApiResult
     */
    public static ApiResult internalError() {
        return new ApiResult(ResultCode.INTERNAL_SERVER_ERROR.getCode(), ResultCode.INTERNAL_SERVER_ERROR.getDesc(), null);
    }

    /***
     * 返回错误码500
     *
     * @param message String 错误消息
     * @return ApiResult
     */
    public static ApiResult internalError(String message) {
        return new ApiResult(ResultCode.INTERNAL_SERVER_ERROR.getCode(), message, null);
    }

    /***
     * 调用成功
     *
     * @return ApiResult
     */
    public static ApiResult ok() {
        return new ApiResult(ResultCode.SUCCESS.getCode(), "OK", null);
    }

    /***
     * 调用成功
     *
     * @param message String 消息内容
     * @return ApiResult
     */
    public static ApiResult ok(String message) {
        return new ApiResult(ResultCode.SUCCESS.getCode(), message, null);
    }

    /***
     * 创建接口返回对象
     *
     * @param code int 状态码
     * @param message String 消息内容
     * @return ApiResult
     */
    public static ApiResult ok(Integer code, String message) {
        return new ApiResult(code, message);
    }

    /***
     * 创建接口返回对象，状态码200
     *
     * @param message String 消息内容
     * @param data Object 消息值
     * @return ApiResult
     */
    public static ApiResult ok(String message, Object data) {
        return new ApiResult(ResultCode.SUCCESS.getCode(), message, data);
    }

    /**
     * 创建接口返回对象
     *
     * @param code String 验证码
     * @param message String 返回消息内容
     * @param data Object 返回数据
     * @return ApiResult
     */
    public static ApiResult ok(Integer code, String message, Object data) {
        return new ApiResult(code, message, data);
    }

    /**
     * 记录已被加锁
     *
     * @param data Object 加锁人
     * @return ApiResult
     */
    public static ApiResult lockByOther(Object data) {
        return new ApiResult(ResultCode.LOCK_BY_OTHER.getCode(), ResultCode.LOCK_BY_OTHER.getDesc(), data);
    }

    /**
     * 记录已被加锁
     *
     * @return ApiResult
     */
    public static ApiResult performFailed() {
        return new ApiResult(ResultCode.PERFORM_FAILED.getCode(), ResultCode.PERFORM_FAILED.getDesc());
    }

    /**
     * 记录未加锁
     *
     * @return ApiResult
     */
    public static ApiResult unlock() {
        return new ApiResult(ResultCode.UNLOCK.getCode(), ResultCode.UNLOCK.getDesc());
    }

    /***
     * 创建接口返回对象
     *
     * @param code int 状态码
     * @param message String 消息内容
     * @param data Object 消息值
     * @return ApiResult
     */
    public static ApiResult build(Integer code, String message, Object data) {
        return new ApiResult(ResultCode.SUCCESS.getCode(), message, data);
    }

    /***
     * 创建接口返回对象
     *
     * @param code int 状态码
     * @param message String 消息内容
     * @return ApiResult
     */
    public static ApiResult build(Integer code, String message) {
        return new ApiResult(code, message, null);
    }

    public ApiResult() { }

    public ApiResult(Integer code, String msg, T data) {
        this.code = code;
        this.message = msg;
        this.data = data;
    }

    public ApiResult(String message) {
        this(ResultCode.SUCCESS.getCode(), message, null);
    }

    public ApiResult(String message, Integer code) {
        this.message = message;
        this.code = code;
    }

    public ApiResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public ApiResult(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.message = resultCode.getDesc();
    }

    public ApiResult(T data) {
        this.code = ResultCode.SUCCESS.getCode();
        this.message = ResultCode.SUCCESS.getDesc();
        this.data = data;
    }

}
