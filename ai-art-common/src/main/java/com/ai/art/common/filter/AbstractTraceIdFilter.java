package com.ai.art.common.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

/**
 * @author xl
 * @version Id: AbstractTraceIdFilter.java, v 0.1 2024/2/18 22:03 Exp $$
 */
@Slf4j
@Component
public class AbstractTraceIdFilter implements Filter {

    private final static String TRACE_ID = "traceId";

    /**
     * 放置traceId
     */
    private void putTraceId(String traceId) {
        // 放入日志请求域
        MDC.put(TRACE_ID, traceId);
    }

    /**
     * 清除traceId
     */
    private void clearTraceId() {
        try {
            MDC.remove(TRACE_ID);
        } catch (Exception e) {
            log.warn("clear traceId error. " + e.getMessage());
        }
    }

    /**
     * 生成traceId
     *
     * @param request
     *            请求
     * @return  traceId
     */
    public static String generateTraceId(HttpServletRequest request) {
        String traceId = UUID.randomUUID().toString().replace("-", "");
        try {
            if (request != null) {
                String requestUri = request.getRequestURI();
                if (requestUri != null) {
                    if (requestUri.startsWith("/")) {
                        requestUri = requestUri.substring(1);
                    }
                    if (requestUri.endsWith("/")) {
                        requestUri = requestUri.substring(0, requestUri.length() - 1);
                    }
                    //                    requestUri = requestUri.replace("/", ".");
                }

                // 如果需要加上用户追踪，这儿可以将用户ID放入 traceId 中
                // String accountId = null;
                // 获取用户ID逻辑
                // traceId = String.join("_", traceId, accountId == null ? "%s" : accountId, requestUri);
                traceId = String.join(":", traceId, requestUri);
            }
        } catch (Exception e) {
            // 异常
            log.error("生成traceId异常,异常信息：{}", e.getMessage());
        }
        return traceId;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        try {
            HttpServletRequest httpServletRequest = ((HttpServletRequest) request);
            // 日志号
            String traceId;
            // 请求头日志号
            String headerTraceId = httpServletRequest.getHeader(TRACE_ID);
            // 请求参数日志号
            String paramTraceId = httpServletRequest.getParameter(TRACE_ID);
            if (StringUtils.isBlank(headerTraceId) && StringUtils.isBlank(paramTraceId)) {
                // 当请求头和请求参数都未传日志号时，生成日志号
                traceId = generateTraceId(httpServletRequest);
                log.debug("generate traceId [{}].", headerTraceId);
            } else {
                // 优先取请求参数的日志号
                traceId = StringUtils.isBlank(paramTraceId) ? headerTraceId : paramTraceId;
                log.debug("obtain traceId [{}] from request header. ", headerTraceId);
            }
            // put
            putTraceId(traceId);
            chain.doFilter(httpServletRequest, response);
        } finally {
            // clear
            clearTraceId();
        }
    }
}
