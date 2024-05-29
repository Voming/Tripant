package mclass.store.tripant.config;
//
//import javax.sql.DataSource;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;
//import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
//import org.springframework.jdbc.core.JdbcTemplate;
//
//@Configuration
//@EnableJdbcRepositories
//@PropertySource("classpath:/keyproperties/apikeys.properties")
public class JdbcConfig/* extends AbstractJdbcConfiguration */{
//
//	
//    @Value("${spring.datasource.driver-class-name}")
//    public String driverClassName;
//
//    @Value("${spring.datasource.url}")
//    public String url;
//
//    @Value("${spring.datasource.username}")
//    public String username;
//
//    @Value("${spring.datasource.password}")
//    public String password;
//
//    // 접근할 DB 정의
//    @Bean
//    public DataSource dataSource() {
//        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
//        dataSourceBuilder.driverClassName(driverClassName);
//        dataSourceBuilder.url(url);
//        dataSourceBuilder.username(username);
//        dataSourceBuilder.password(password);
//        return dataSourceBuilder.build();
//    }
//
//    // 사용할 JdbcTemplate에 대해서 정의 진행
//    @Bean
//    public JdbcTemplate jdbcTemplate(){
//        return new JdbcTemplate(dataSource());
//    }
}