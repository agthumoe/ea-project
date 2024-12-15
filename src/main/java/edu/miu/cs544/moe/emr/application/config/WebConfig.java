package edu.miu.cs544.moe.emr.application.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.data.domain.AuditorAware;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.util.*;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.defaultContentType(MediaType.APPLICATION_JSON);
        configurer.mediaTypes(new HashMap<>() {{
            put("json", MediaType.APPLICATION_JSON);
            put("xml", MediaType.APPLICATION_XML);
        }});
        configurer.favorParameter(true);
    }

//    @Bean
//    public MessageSource messageSource() {
//        ResourceBundleMessageSource resourceBundleMessageSource= new ResourceBundleMessageSource();
//        resourceBundleMessageSource.setBasename("i18n/messages");
//        resourceBundleMessageSource.setDefaultEncoding("UTF-8");
//        return resourceBundleMessageSource;
//    }

    @Bean
    public LocaleResolver localeResolver(){
        AcceptHeaderLocaleResolver localeResolver= new AcceptHeaderLocaleResolver();
        localeResolver.setDefaultLocale(Locale.US);
        return localeResolver;
    }

    @Bean
    public AuditorAware<String> auditorAware() {
        return () -> Optional.of("system");
    }
}
