package com.LinSY.backend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan("com.LinSY.backend.mapper")
	public class OnlineStoreApplication {

		public static void main(String[] args) {
		SpringApplication.run(OnlineStoreApplication.class, args);
	}
}