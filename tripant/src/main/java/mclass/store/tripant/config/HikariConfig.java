package mclass.store.tripant.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariDataSource;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class HikariConfig{
	
	@Value("${spring.datasource.hikari.jdbc-log4j2-driverClassName}")
	private String driverClassName;
	@Value("${spring.datasource.hikari.jdbc-log4j2-url}")
	private String hikariUrl;
	@Value("${spring.datasource.hikari.username}")
	private String username;
	@Value("${spring.datasource.hikari.password}")
	private String password;

	@Bean
	DataSource dataSource() {
		com.zaxxer.hikari.HikariConfig hikariConfig = new com.zaxxer.hikari.HikariConfig();

		hikariConfig.setJdbcUrl(driverClassName);
		hikariConfig.setJdbcUrl(hikariUrl);
		hikariConfig.setUsername(username);
		hikariConfig.setPassword(password);

		return new HikariDataSource(hikariConfig);
    }
}