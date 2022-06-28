package com.wisdge.web;

/**
 *  <p> 响应码枚举 - 可参考HTTP状态码的语义 </p>
 */
public enum ResultCode {
    CONTINUE( 100, "继续。客户端应继续其请求"),
    SWITCHING_PROTOCALS( 101, "切换协议。服务器根据客户端的请求切换协议。只能切换到更高级的协议，例如，切换到HTTP的新版本协议"),

    SUCCESS( 200, "SUCCESS" ),
    CREATED(201, "已创建。成功请求并创建了新的资源"),
    ACCEPTED(202, "已接受。已经接受请求，但未处理完成"),
    NON_AUTHORITATIVE_INFORMATION(203, "非授权信息。请求成功。但返回的meta信息不在原始的服务器，而是一个副本"),
    NO_CONTENT(204, "无内容。服务器成功处理，但未返回内容。在未更新网页的情况下，可确保浏览器继续显示当前文档"),
    RESET_CONTENT(205, "重置内容。服务器处理成功，用户终端（例如：浏览器）应重置文档视图。可通过此返回码清除浏览器的表单域"),
    PARTIAL_CONTENT(206, "部分内容。服务器成功处理了部分GET请求"),

    MULTIPLE_CHOICES(300, "多种选择。请求的资源可包括多个位置，相应可返回一个资源特征与地址的列表用于用户终端（例如：浏览器）选择"),
    MOVED_PERMANENTLY(301, "永久移动。请求的资源已被永久的移动到新URI，返回信息会包括新的URI，浏览器会自动定向到新URI。今后任何新的请求都应使用新的URI代替"),
    FOUND(302, "临时移动。与301类似。但资源只是临时被移动。客户端应继续使用原有URI"),
    SEE_OTHER(303, "查看其它地址。与301类似。使用GET和POST请求查看"),
    NOT_MODIFIED(304, "未修改。所请求的资源未修改，服务器返回此状态码时，不会返回任何资源。客户端通常会缓存访问过的资源，通过提供一个头信息指出客户端希望只返回在指定日期之后修改的资源"),
    USE_PROXY(305, "使用代理。所请求的资源必须通过代理访问"),
    UNUSED(306, "已经被废弃的HTTP状态码"),
    TEMPORARY_REDIRECT(307, "临时重定向。与302类似。使用GET请求重定向"),

    BAD_REQUEST( 400, "客户端请求的语法错误" ),
    UNAUTHORIZED( 401, "认证失效" ),
    USER_UNAUTHORIZED( 402, "用户名或密码不正确" ),
    FORBIDDEN( 403, "访问被拒绝" ),
    NOT_FOUND( 404, "接口不存在" ),
    METHOD_NOT_ALLOWED(405, "客户端请求中的方法被禁止"),
    NOT_ACCEPTABLE(406, "服务器无法根据客户端请求的内容特性完成请求"),
    PROXY_AUTHENTICATION_REQUIRED(407, "请求要求代理的身份认证，与401类似，但请求者应当使用代理进行授权"),
    REQUEST_TIMEOUT(408, "服务器等待客户端发送的请求时间过长，超时"),
    CONFLICT(409, "服务器完成客户端的PUT请求是可能返回此代码，服务器处理请求时发生了冲突"),
    GONE(410, "客户端请求的资源已经不存在。如果资源以前有现在被永久删除了可使用410代码，可通过301代码指定资源的新位置"),
    LENGTH_REQUIRED(411, "服务器无法处理客户端发送的不带Content-Length的请求信息"),
    PRECONDITION_FAILED(412, "客户端请求信息的先决条件错误"),
    REQUEST_ENTITY_TOO_LARGE(413, "由于请求的实体过大，服务器无法处理。为防止客户端的连续请求，服务器可能会关闭连接。如果只是服务器暂时无法处理，则会包含一个Retry-After的响应信息"),
    REQUEST_URI_TOO_LARGE(414, "请求的URI过长（URI通常为网址），服务器无法处理"),
    UNSUPPORTED_MEDIA_TYPE(415, "服务器无法处理请求附带的媒体格式"),
    REQUESTED_RANGE_NOT_SATISFIABLE(416, "客户端请求的范围无效"),
    EXPECTATION_FAILED(417, "服务器无法满足Expect的请求头信息"),

    INTERNAL_SERVER_ERROR( 500, "服务器内部错误" ),
    NOT_IMPLEMENTED(501, "服务器不支持请求的功能"),
    BAD_GATEWAY(502, "充当网关或代理的服务器，从远端服务器接收到了一个无效的请求"),
    SERVICE_UNAVAILABLE(503, "由于超载或系统维护，服务器暂时的无法处理客户端的请求。延时的长度可包含在服务器的RETRY-AFTER头信息中"),
    GATEWAY_TIMEOUT(504, "充当网关或代理的服务器，未及时从远端服务器获取请求"),
    HTTP_VERSION_NOT_SUPPORTED(505, "服务器不支持请求的HTTP协议的版本，无法完成处理"),

    LOCK_BY_OTHER(509, "记录已加锁"),
    UNLOCK(510, "记录未加锁"),

    PERFORM_FAILED(520, "ES操作失败"),

    UNAUTHORIZED_EXPIRED(4001, "凭据已过期"),
    UNAUTHORIZED_EXPIRED_BY_KICKOFF(4002, "凭据已失效"),
    UNAUTHORIZED_ILLEGAL(4003, "不合法的凭据");

    private int code;
    private String desc;

    ResultCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
