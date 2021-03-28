package binlist.demo.api;

import binlist.demo.api.security.filters.MyAuthenticationFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}


	//Add custom request filter for JWT
	@Bean
	public FilterRegistrationBean<MyAuthenticationFilter> filterRegistrationBean(){
		FilterRegistrationBean<MyAuthenticationFilter> filterBean = new FilterRegistrationBean<>();
		filterBean.setFilter(new MyAuthenticationFilter());
		filterBean.addUrlPatterns(new String[] {"/countries/*", "/payment-cards-cost"});
		return filterBean;
	}

}
