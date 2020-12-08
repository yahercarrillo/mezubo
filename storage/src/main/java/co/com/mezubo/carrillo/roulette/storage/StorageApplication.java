package co.com.mezubo.carrillo.roulette.storage;

import co.com.mezubo.carrillo.roulette.storage.config.RequestBetFilter;
import co.com.mezubo.carrillo.roulette.storage.service.UsersServiceCrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class StorageApplication {

	@Autowired
	UsersServiceCrud usersService;

	@Bean
	public FilterRegistrationBean<RequestBetFilter> loggingFilter(){
		FilterRegistrationBean<RequestBetFilter> registrationBean
				= new FilterRegistrationBean<>();

		registrationBean.setFilter(new RequestBetFilter(usersService));
		registrationBean.addUrlPatterns("/v1/roulette/createBet/*","/v1/roulette/updateBet/*");

		return registrationBean;
	}

	public static void main(String[] args) {
		SpringApplication.run(StorageApplication.class, args);
	}

}
