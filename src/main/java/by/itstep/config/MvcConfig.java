package by.itstep.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Value("${homeWork.path}")
    private String homeWorkPath;

    @Value("${solution.path}")
    private String solutionPath;

    @Value("${avatar.path}")
    private String avatarPath;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("hw/**").addResourceLocations("file://" + homeWorkPath + "/");
        registry.addResourceHandler("solution/**").addResourceLocations("file://" + solutionPath + "/");
        registry.addResourceHandler("static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("avatar/**").addResourceLocations("file://" + avatarPath + "/");
    }
}
