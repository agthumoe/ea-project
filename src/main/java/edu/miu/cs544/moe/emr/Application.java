package edu.miu.cs544.moe.emr;

import edu.miu.cs544.moe.emr.application.App;
import edu.miu.cs544.moe.emr.application.AppInfo;
import edu.miu.cs544.moe.emr.application.AppInfoEditor;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.beans.factory.config.CustomEditorConfigurer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.jms.annotation.EnableJms;

import java.beans.PropertyEditor;
import java.util.HashMap;
import java.util.Map;

@OpenAPIDefinition(info = @Info(
        title = "EMR API",
        description = "RESTful API for managing electronic medical records, enabling efficient storage, retrieval, and updates of patient data.",
        version = "0.0.1",
        contact = @Contact(name = "Aung Thu Moe", email = "amoe@miu.edu")
))
@SpringBootApplication
@EnableAspectJAutoProxy
@EnableJms
@EnableConfigurationProperties(App.class)
@EnableJpaAuditing
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public static CustomEditorConfigurer customEditorConfigurer() {
        CustomEditorConfigurer configurer = new CustomEditorConfigurer();
        Map<Class<?>, Class<? extends PropertyEditor>> editors = new HashMap<>();
        editors.put(AppInfo.class, AppInfoEditor.class);
        configurer.setCustomEditors(editors);
        return configurer;
    }
}
