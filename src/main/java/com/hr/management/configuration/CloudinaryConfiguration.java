package com.hr.management.configuration;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

import static java.lang.System.getenv;

@Configuration
public class CloudinaryConfiguration {
    private final String CLOUDINARY_NAME = getenv("CLOUD_NAME");
    private final String API_KEY_CLOUDINARY = getenv("API_KEY");
    private final String API_SECRET_CLOUDINARY = getenv("API_SECRET");
    @Bean
    public Cloudinary cloudinary(){
        final Map<String, String> config = new HashMap<>();
        config.put("cloud_name", CLOUDINARY_NAME);
        config.put("api_key", API_KEY_CLOUDINARY);
        config.put("api_secret", API_SECRET_CLOUDINARY);
        return new Cloudinary(config);
    }
}
