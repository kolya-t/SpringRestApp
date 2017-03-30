package ru.eninja.config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;
import ru.eninja.config.spring.ThymeleafConfig;

import javax.servlet.*;

public class WebConfig implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext rootContext =
                new AnnotationConfigWebApplicationContext();

        rootContext.register(ThymeleafConfig.class);

        ServletRegistration.Dynamic dispatcherServlet =
                servletContext.addServlet("dispatcher", new DispatcherServlet(rootContext));

        dispatcherServlet.setLoadOnStartup(1);
        dispatcherServlet.addMapping("/");

        // charset filter
        FilterRegistration.Dynamic characterEncoding =
                servletContext.addFilter("characterEncoding", characterEncodingFilter());
        characterEncoding.addMappingForUrlPatterns(null, true, "/*");
    }

    @Bean
    public CharacterEncodingFilter characterEncodingFilter() {
        return new CharacterEncodingFilter("UTF-8", true, true);
    }
}
