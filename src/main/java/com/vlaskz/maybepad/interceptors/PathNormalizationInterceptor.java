package com.vlaskz.maybepad.interceptors;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class PathNormalizationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String originalPath = request.getRequestURI();
        String normalizedPath = normalizePath(originalPath);

        if (!originalPath.equals(normalizedPath)) {
            response.sendRedirect(normalizedPath);
            return false;  // Não continue o processamento da solicitação original
        }

        return true;  // Continue o processamento da solicitação original
    }

    private String normalizePath(String path) {
        // Remova caracteres não ASCII
        path = path.replaceAll("[^\\p{ASCII}]", "");

        // Substitua espaços por hífens
        path = path.replace(" ", "-");

        // Remova caracteres especiais, mantendo hífens, barras e sublinhados
        path = path.replaceAll("[^a-zA-Z0-9/-_]", "");

        return path;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}
