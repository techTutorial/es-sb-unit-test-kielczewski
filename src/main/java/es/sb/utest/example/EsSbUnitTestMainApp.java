package es.sb.utest.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
//import org.springframework.boot.context.web.SpringBootServletInitializer;//version 1
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class EsSbUnitTestMainApp extends SpringBootServletInitializer {

    public static void main(final String[] args) {
        SpringApplication.run(EsSbUnitTestMainApp.class, args);
    }

    @Override
    protected final SpringApplicationBuilder configure(final SpringApplicationBuilder application) {
        return application.sources(EsSbUnitTestMainApp.class);
    }
    
}
