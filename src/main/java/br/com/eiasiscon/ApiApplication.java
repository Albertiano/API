package br.com.eiasiscon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@EnableConfigurationProperties
public class ApiApplication extends SpringBootServletInitializer {

	  @Override
	  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	    return application.sources(ApiApplication.class);
	  }

	public static void main(String[] arg) {
		SpringApplication.run(ApiApplication.class, arg);
	}
    
}
