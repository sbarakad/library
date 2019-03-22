package com.library.search;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DBSeachControllerBean {
	@Bean
	public DBSeachController getDBSeachControllerInstance() {
		return new DBSeachController();
	}
}
