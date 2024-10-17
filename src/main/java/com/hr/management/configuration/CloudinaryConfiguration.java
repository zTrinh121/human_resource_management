package com.hr.management.configuration;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudinaryConfiguration {
    @Bean
    public Cloudinary cloudinary(){
        final Map<String, String> config = new HashMap<>();
        config.put("cloud_name", "dzhmeuvdf");
        config.put("api_key", "397222541129283");
        config.put("api_secret", "iGBiZxMV-bDVnsL0kIxSZxIwx8s");
        return new Cloudinary(config);
    }
}
