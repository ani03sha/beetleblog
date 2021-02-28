package com.beetleblog.app.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.beetleblog.app.constants.ApplicationConstants.APP_DESCRIPTION;
import static com.beetleblog.app.constants.ApplicationConstants.APP_NAME;

@Configuration
public class OpenAPIConfiguration {

    @Bean
    public OpenAPI beetleOpenAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info().title(APP_NAME).description(APP_DESCRIPTION));
    }
}
