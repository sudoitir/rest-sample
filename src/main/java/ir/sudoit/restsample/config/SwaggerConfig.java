package ir.sudoit.restsample.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {


    @Bean
    public OpenAPI customOpenAPI(@Value ("${springdoc.version}") String appVersion) {
        return new OpenAPI()
                .info(new Info()
                        .title("Learn REST API")
                        .version(appVersion)
                        .description(
                                "Example of REST API with documentation and validation, Service call with feign and rest template and etc.")
                        .termsOfService("https://google.com")
                        .license(new License().name("Apache 2.0").url("https://google.com")));
    }

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("location")
                .pathsToMatch("/api/locations/**")
                .displayName("Location Api")
                .build();
    }

}
