package edu.miu.cs544.moe.emr.config;

import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.HeaderParameter;
import io.swagger.v3.oas.models.parameters.Parameter;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenApiCustomizer globalHeaderOpenApiCustomiser() {
        return openApi -> {
            // Define the Accept-Language header parameter
            Parameter acceptLanguageHeader = new HeaderParameter()
                    .in("header")
                    .schema(new StringSchema())
                    .name("Accept-Language")
                    .description("Preferred language for the response (e.g., en, fr, de)")
                    .required(false); // Set to true if the header is mandatory

            // Iterate over all paths and operations to add the header
            openApi.getPaths().values().forEach(pathItem ->
                    pathItem.readOperations().forEach(operation ->
                            operation.addParametersItem(acceptLanguageHeader)
                    )
            );
        };
    }
}
