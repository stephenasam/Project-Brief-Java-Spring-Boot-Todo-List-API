package com.example.todosapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Bean;
import org.sqlite.SQLiteDataSource;

import javax.sql.DataSource;

@SpringBootApplication
public class TodosapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodosapiApplication.class, args);
	}

	@Bean
	public DataSource dataSource() {
		SQLiteDataSource dataSource = new SQLiteDataSource();
		dataSource.setUrl("jdbc:sqlite:todos.db");
		return dataSource;
	}

}
