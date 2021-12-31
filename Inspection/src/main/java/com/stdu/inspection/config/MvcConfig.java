/**
 * @Author: Yan Jingcun
 * @Date: 2021/11/9
 * @Description:
 * @Version: 1.0
 */

package com.stdu.inspection.config;

import com.stdu.inspection.utils.ConstUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {


    /**
     * 将jar文件下的对应静态资源文件路径对应到磁盘的路径(根据个人的情况修改"file:static/"的static的值)
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String location = "file:" + ConstUtil.DAMAGE_PICTURE;
        registry.
                addResourceHandler("/static/**").
                addResourceLocations("classpath:/static/", location);
    }


    /**
     * 注册 URL 拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AllowOriginIntercepter());
    }

}
