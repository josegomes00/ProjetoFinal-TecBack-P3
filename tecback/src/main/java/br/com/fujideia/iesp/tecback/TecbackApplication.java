package br.com.fujideia.iesp.tecback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.validation.annotation.Validated;

@SpringBootApplication
@Validated
public class TecbackApplication {
	public static void main(String[] args) {
		SpringApplication.run(TecbackApplication.class, args);
	}

}
