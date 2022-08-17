package com.example.messaging;

import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
//import org.bson.types.ObjectId;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class MessagingApplication {

	public static void main(String[] args) {
		SpringApplication.run(MessagingApplication.class, args);
	}

	/*@Bean
	public Jackson2ObjectMapperBuilderCustomizer customizer()
	{
		return builder -> builder.serializerByType(ObjectId.class,new ToStringSerializer());
	}*/
	/*@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowCredentials(true)
						.allowedOrigins("http://localhost:3000")
						.allowedMethods("*");
			}
		};
	}*/

}

