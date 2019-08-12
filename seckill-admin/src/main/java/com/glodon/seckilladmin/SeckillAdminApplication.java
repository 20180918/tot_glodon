package com.glodon.seckilladmin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
@MapperScan("com.glodon.seckilladmin.mapper")
public class SeckillAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(SeckillAdminApplication.class, args);
	}

}
