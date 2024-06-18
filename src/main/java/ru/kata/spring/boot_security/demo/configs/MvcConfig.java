package ru.kata.spring.boot_security.demo.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

        @Override
        public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
            configurer.favorParameter(true)
                    .parameterName("format")
                    .defaultContentType(MediaType.TEXT_HTML)
                    .mediaType("html", MediaType.TEXT_HTML)
                    .mediaType("json", MediaType.APPLICATION_JSON);
        }



    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
            registry.addViewController("/api/user").setViewName("user");
            registry.addViewController("/api/admin").setViewName("admin");

        //registry.addViewController("/").setViewName("/login");
    }
}
