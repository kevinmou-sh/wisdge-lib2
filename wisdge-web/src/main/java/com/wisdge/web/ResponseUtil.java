package com.wisdge.web;

import com.wisdge.utils.JSonUtils;
import lombok.extern.slf4j.Slf4j;
import jakarta.servlet.ServletResponse;
import java.io.PrintWriter;

/**
 * 使用response输出JSON
 */
@Slf4j
public class ResponseUtil {

    /**
     * 使用response输出JSON
     * @param response
     * @param result
     */
    public static void out(ServletResponse response, ApiResult result) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        try(PrintWriter writer = response.getWriter()) {
            writer.println(JSonUtils.parse(result));
            writer.flush();
        } catch (Exception e) {
            log.error("输出JSON出错", e);
        }
    }

    /**
     * 使用response输出JSON
     * @param response
     * @param msg
     * @param status
     */
    public static void out(ServletResponse response, String msg, Integer status) {
        out(response, new ApiResult(status, msg));
    }

}
