package com.glodon.seckillweb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.glodon.seckillweb.mapper")
public class SeckillWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(SeckillWebApplication.class, args);
	}

}
