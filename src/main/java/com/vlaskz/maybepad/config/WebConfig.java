package com.vlaskz.maybepad.config;


import com.vlaskz.maybepad.interceptors.PathNormalizationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final PathNormalizationInterceptor pathNormalizationInterceptor;

    @Autowired
    public WebConfig(PathNormalizationInterceptor pathNormalizationInterceptor) {
        this.pathNormalizationInterceptor = pathNormalizationInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(pathNormalizationInterceptor)
                .addPathPatterns("/**");
    }
}