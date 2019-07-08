package tw.com.cathaylife.testproject.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class DataSourceConfig {

    @Bean(name = "localDataSource")
    @ConfigurationProperties(prefix = "custom.datasource.local")
    public DataSource localDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "db2DataSource")
    @ConfigurationProperties(prefix = "custom.datasource.db2")
    public DataSource db2DataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "mysqlDataSource")
    @ConfigurationProperties(prefix = "custom.datasource.mysql")
    public DataSource mysqlDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "mycatDataSource")
    @ConfigurationProperties(prefix = "custom.datasource.mycat")
    public DataSource mycatDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "localJdbcTemplate")
    public JdbcTemplate localJdbcTemplate(@Qualifier("localDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean(name = "db2JdbcTemplate")
    public JdbcTemplate db2JdbcTemplate(@Qualifier("db2DataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean(name = "mysqlJdbcTemplate")
    public JdbcTemplate mysqlJdbcTemplate(@Qualifier("mysqlDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean(name = "mycatJdbcTemplate")
    public JdbcTemplate mycatJdbcTemplate(@Qualifier("mycatDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}