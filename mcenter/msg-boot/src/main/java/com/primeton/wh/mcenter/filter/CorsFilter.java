package com.primeton.wh.mcenter.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 跨越过滤器
 *
 * @author primeton
 * @date 2018/06/04
 */
@Slf4j
@Order(1)
@Component
public class CorsFilter implements Filter {
    @Override
    public void init(final FilterConfig filterConfig) {
        log.debug("==> CorsFilter init");
    }

    @Override
    public void doFilter(
            final ServletRequest servletRequest,
            final ServletResponse servletResponse,
            final FilterChain filterChain)
            throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;

        // 仅在非生产环境下生效
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Headers", "*");
        // 明确允许通过的方法，不建议使用 *
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Expose-Headers", "*");

        // 预请求后，直接返回
        // 返回码必须为 200 否则视为请求失败
        if (HttpMethod.OPTIONS.matches(request.getMethod())) {
            return;
        }
        String method = request.getMethod();
        if (method.equalsIgnoreCase("OPTIONS")) {
            response.getOutputStream().write("Success".getBytes("utf-8"));
        } else {
            filterChain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        log.debug("==> CorsFilter destroy");
    }
}
