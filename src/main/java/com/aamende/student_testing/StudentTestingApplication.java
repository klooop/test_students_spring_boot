package com.aamende.student_testing;

import com.aamende.student_testing.configs.AppProps;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(AppProps.class)
public class StudentTestingApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentTestingApplication.class, args);
    }

}
