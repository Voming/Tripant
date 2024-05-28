package mclass.store.tripant.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class HikariConfig{
	@Value("${spring.datasource.username}")  // 1
	private String username;

	@Value("${spring.datasource.password}")  // 1
	private String password;

	@Value("${spring.datasource.url}")  // 1
	private String url;

	// 2
	@Bean
	public DataSource dataSource() {
		com.zaxxer.hikari.HikariConfig hikariConfig = new com.zaxxer.hikari.HikariConfig();  // 3

		hikariConfig.setJdbcUrl(url);
		hikariConfig.setUsername(username);
		hikariConfig.setPassword(password);

		return new HikariDataSource(hikariConfig); // 4
    }
}