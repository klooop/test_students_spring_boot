package com.aamende.student_testing.configs;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "application")
public class AppProps {
    private String fileName;
}
