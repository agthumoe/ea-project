package edu.miu.cs544.moe.emr.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.HeaderParameter;
import io.swagger.v3.oas.models.parameters.Parameter;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(info = @Info(
        title = "${application.title}",
        description = "${application.description}",
        version = "${application.version}",
        contact = @Contact(name = "Aung Thu Moe", email = "amoe@miu.edu")
))
@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenApiCustomizer globalHeaderOpenApiCustomizer() {
        return openApi -> {
            Parameter acceptLanguageHeader = new HeaderParameter()
                    .in("header")
                    .schema(new StringSchema())
                    .name("Accept-Language")
                    .description("Preferred language for the response (e.g., en, fr, de)")
                    .required(false);
            openApi.getPaths().values().forEach(pathItem ->
                    pathItem.readOperations().forEach(operation -> {
                        operation.addParametersItem(acceptLanguageHeader);
                    })
            );
        };
    }
}
