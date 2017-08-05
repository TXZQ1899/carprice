package com.askprice.carprice.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {
	
	@Value("${cors.domain}")
    private String filterDomain;
	
    private CorsConfiguration buildConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        
        String[] domains = filterDomain.split(",");
        for(String domain : domains) 
        {
        	corsConfiguration.addAllowedOrigin(domain);
        }
        
        corsConfiguration.addAllowedHeader("*"); 
        corsConfiguration.addAllowedMethod("*"); 
        return corsConfiguration;
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", buildConfig()); // 4
        return new CorsFilter(source);
    }
}



