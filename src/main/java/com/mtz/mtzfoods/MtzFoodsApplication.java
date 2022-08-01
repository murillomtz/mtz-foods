package com.mtz.mtzfoods;

import com.mtz.mtzfoods.infrastructure.repository.CustomJpaRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.TimeZone;

@SpringBootApplication
/**
 * repositoryBaseClass = customiza e informa qual é o repository base,
 * OBS: Sempre apontar para interface
 * */
@EnableJpaRepositories(repositoryBaseClass = CustomJpaRepositoryImpl.class)
public class MtzFoodsApplication {


	public static void main(String[] args) {

		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		SpringApplication.run(MtzFoodsApplication.class, args);
	}

}
