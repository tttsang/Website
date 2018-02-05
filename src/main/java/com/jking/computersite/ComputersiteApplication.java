package com.jking.computersite;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.jking.computersite.mapper")
public class ComputersiteApplication {

	public static void main(String[] args) {
		SpringApplication.run(ComputersiteApplication.class, args);
	}
}
