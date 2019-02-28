package br.com.curso.apispringboot.apispringboot;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiSpringbootApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ApiSpringbootApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	}
}

