package com.hr.management;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan({"com.hr.management.mapper"})
public class ManagementApplication {

	public static void main(String[] args) {

		SpringApplication.run(ManagementApplication.class, args);
	}

}
