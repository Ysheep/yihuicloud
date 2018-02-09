package com.qzdsoft;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.qzdsoft.eshop.interceptor.PCUserLoginInterceptor;
import com.qzdsoft.eshop.interceptor.PermissionInterceptor;
import com.qzdsoft.eshop.interceptor.UserLoginInterceptor;

@Configuration
@ComponentScan(basePackageClasses = App.class, useDefaultFilters = true)
public class WebAppConfig extends WebMvcConfigurerAdapter
{

    
    @Autowired
    private UserLoginInterceptor userLoginInterceptor;
    @Autowired
    private PCUserLoginInterceptor pcUserLoginInterceptor;
    @Autowired
    private PermissionInterceptor permissionInterceptor;
    
    @Override
    public void addInterceptors(InterceptorRegistry registry)
    {
    	registry.addInterceptor(pcUserLoginInterceptor).addPathPatterns("/user/**");
        registry.addInterceptor(userLoginInterceptor).addPathPatterns("/admin/**")
        .excludePathPatterns("/admin")
        .excludePathPatterns("/admin/login*")
        .excludePathPatterns("/admin/kaptcha.jpg");
        
        registry.addInterceptor(permissionInterceptor).addPathPatterns("/admin/**")
        .excludePathPatterns("/admin")
        .excludePathPatterns("/admin/login*")
        .excludePathPatterns("/admin/kaptcha.jpg")
        .excludePathPatterns("/kaptch")
        .excludePathPatterns("/forget")
        .excludePathPatterns("/alertPwd")
        .excludePathPatterns("/sendSmsCode")
        .excludePathPatterns("/error")
        .excludePathPatterns("/401")
        .excludePathPatterns("/404")
        .excludePathPatterns("/500")
        .excludePathPatterns("/400")
        .excludePathPatterns("/admin/index")
        
        .excludePathPatterns("/wap/**").excludePathPatterns("/admin/logout*");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry)
    {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/**");
    }


    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer()
    {

        return (container -> {
              ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND,"/404"); 
              ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500");
              ErrorPage error400Page = new ErrorPage(HttpStatus.BAD_REQUEST, "/400");
              container.addErrorPages( error404Page, error500Page,error400Page);
        });
    }

}
