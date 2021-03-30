package binlist.demo.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

import binlist.demo.api.security.filters.MyAuthenticationFilter;


@SpringBootApplication
public class Application {
	
	
	//Add custom request filter for JWT
	@Bean
	public FilterRegistrationBean<MyAuthenticationFilter> filterRegistrationBean(){
		FilterRegistrationBean<MyAuthenticationFilter> filterBean = new FilterRegistrationBean<>();
		filterBean.setFilter(new MyAuthenticationFilter());
		filterBean.addUrlPatterns(new String[] {"/countries/*", "/payment-cards-cost"});
		return filterBean;
	}
	
	
	@Bean
	public WebClient.Builder getWebClientBuilder(){
		return WebClient.builder();
	}
	

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}


}
