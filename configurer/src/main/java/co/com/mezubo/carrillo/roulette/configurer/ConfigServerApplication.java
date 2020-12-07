package co.com.mezubo.carrillo.roulette.configurer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Interceptor para la exposicion y captura de errores
 * para un mismo formato
 * @author Yaher Carrillo
 * @date 08/10/2020
 * @since 08/10/2020
 */
@SpringBootApplication
public class ConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigServerApplication.class, args);
	}

}
