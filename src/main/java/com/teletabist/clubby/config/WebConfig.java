package com.teletabist.clubby.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
    @Bean
    @Description("Thymeleaf template resolver serving HTML 5")
    public ClassLoaderTemplateResolver templateResolver(){
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("views/");
        templateResolver.setCacheable(false);
        templateResolver.setSuffix(".thyme.html");
        templateResolver.setTemplateMode("HTML");
        templateResolver.setCharacterEncoding("UTF-8");
        
        return templateResolver;
    }

    @Bean
    @Description("Thymeleaf template engine with Spring")
    public SpringTemplateEngine templateEngine(){
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.addDialect(new SpringSecurityDialect());
        templateEngine.setTemplateResolver(this.templateResolver());
        return templateEngine;
    }

    @Bean
    @Description("Thymeleaf view resolver")
    public ViewResolver viewResolver(){
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(this.templateEngine());
        viewResolver.setCharacterEncoding("UTF-8");
        return viewResolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/public/**")
                .addResourceLocations("classpath:/public/");
    }

    @Bean
    public SpringSecurityDialect securityDialect() {
        return new SpringSecurityDialect();
    }
}