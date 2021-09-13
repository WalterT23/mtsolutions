package proyecto2.mtsolutions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.Duration;

@SpringBootApplication
@EnableScheduling
@EnableAsync
public class MtsolutionsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MtsolutionsApplication.class, args);
	}

	@Bean
	public RestTemplate getresttemplate() {
		RestTemplateBuilder res = new RestTemplateBuilder();
		res.setConnectTimeout(Duration.ofMillis(300000));
		res.setReadTimeout(Duration.ofMillis(300000));
		return res.build();
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedOrigins("*")
						.allowedMethods("*")
						.allowedHeaders("*");
			}
		};
	}

}
