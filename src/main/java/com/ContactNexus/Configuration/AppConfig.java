package com.ContactNexus.Configuration;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Value("${cloudinary.cloud.name}")
    private String cloudName;
    @Value("${cloudinary.api.key}")
    private String ApiKey;
    @Value("${cloudinary.api.secret}")
    private  String Secret;

    @Bean
    public Cloudinary  cloudinary(){


        return new Cloudinary(
                ObjectUtils.asMap(
                        "cloud_name",cloudName,
                                    "api_key",ApiKey,
                                    "api_secret",Secret
                )
       );
    }
}