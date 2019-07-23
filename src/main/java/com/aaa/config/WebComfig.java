package com.aaa.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;


import javax.servlet.DispatcherType;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public class WebComfig implements WebApplicationInitializer{
    @Override
    public void onStartup(javax.servlet.ServletContext servletContext) throws ServletException {

        //初始化容器
        AnnotationConfigWebApplicationContext ac=new  AnnotationConfigWebApplicationContext();
        //加载配置类
        ac.register(SpringConfig.class, SprimgMvcConfig.class);
        //设置servlet上下文
        ac.setServletContext(servletContext);
        //设置前端控制器
        ServletRegistration.Dynamic dynamic = servletContext.addServlet("person", new DispatcherServlet(ac));
        //设置启动顺序
        dynamic.setLoadOnStartup(1);
        dynamic.addMapping("*.do");
        //添加servlet剩下文监听
        servletContext.addListener(new RequestContextListener());
        //字符编码过滤器
        javax.servlet.FilterRegistration.Dynamic addFilter=servletContext.addFilter("filterEncoding",new CharacterEncodingFilter());
        //添加初始化参数
        Map<String,String> map=new HashMap<String,String>();
        map.put("encoding","UTF-8");
        map.put("forceEncoding","true");
        addFilter.setInitParameters(map);
        EnumSet<DispatcherType> dispatcherTypes=EnumSet.allOf(DispatcherType.class);
        dispatcherTypes.add(DispatcherType.REQUEST);
        dispatcherTypes.add(DispatcherType.FORWARD);
        //设置过滤请求的范围
        addFilter.addMappingForUrlPatterns(dispatcherTypes,true,"/*");

    }
}
