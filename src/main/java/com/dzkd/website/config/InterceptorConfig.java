package com.dzkd.website.config;


import com.dzkd.website.interceptor.AdminInterceptor;
import com.dzkd.website.interceptor.StuInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    private AdminInterceptor adminInterceptor;
    private StuInterceptor stuInterceptor;

    @Autowired
    public InterceptorConfig(AdminInterceptor adminInterceptor, StuInterceptor stuInterceptor) {
        this.adminInterceptor = adminInterceptor;
        this.stuInterceptor = stuInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(adminInterceptor).addPathPatterns("/admin/*");
        registry.addInterceptor(stuInterceptor).addPathPatterns("/stu/*");
    }
}
