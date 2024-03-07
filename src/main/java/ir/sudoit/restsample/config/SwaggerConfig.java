package ir.sudoit.restsample.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("location")
                .pathsToMatch("/api/locations/**")
                .displayName("Location Api")
                .build();
    }

}
