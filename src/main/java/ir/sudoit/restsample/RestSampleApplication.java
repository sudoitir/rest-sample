package ir.sudoit.restsample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableFeignClients
@EnableWebMvc
@SpringBootApplication
public class RestSampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestSampleApplication.class, args);
    }

}
