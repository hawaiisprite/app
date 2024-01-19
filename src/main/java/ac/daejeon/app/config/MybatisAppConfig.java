package ac.daejeon.app.config;


import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.io.IOException;


@Configuration
public class MybatisAppConfig {

    @Value("${spring.app.datasource.driver-class-name}")
    private String driverClassName;

    @Value("${spring.app.datasource.url}")
    private String url;

    @Value("${spring.app.datasource.username}")
    private String username;

    @Value("${spring.app.datasource.password}")
    private String password;

    @Autowired
    ApplicationContext applicationContext;


    @Bean
    public DataSource erpDtaSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        return dataSource;
    }

    @Bean
    public SqlSessionFactoryBean erpSqlSessionFactory(DataSource dataSource) throws IOException {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();

        factoryBean.setDataSource(dataSource);
        factoryBean.setConfigLocation(applicationContext.getResource("classpath:/mybatis/config/mybatis-config.xml"));
        factoryBean.setMapperLocations(applicationContext.getResources("classpath:/mybatis/mapper/**/*Mapper.xml"));
        factoryBean.setMapperLocations(applicationContext.getResources("classpath:/mybatis/mapper/*Mapper.xml"));
        return factoryBean;
    }


    @Bean
    public SqlSessionTemplate erpSqlSession(@Qualifier("erpSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}