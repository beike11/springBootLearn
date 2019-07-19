package com.stevenw.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * @author stevenw
 * @date 2019/7/15
 */
@Configuration
@MapperScan(basePackages= DataSourceConfig1.PACKAGE, sqlSessionFactoryRef = "sqlSessionFactory1")
public class DataSourceConfig1 {
    static final String PACKAGE = "com.stevenw.mysqldao1";
    static final String MAPPER_LOCATION = "classpath:mapper/mysql1/*.xml";

    @Primary
    @Bean(name = "mysql1DataSource")
    @ConfigurationProperties("spring.datasource.druid.mysql1")
    public DataSource dataSourceMysqlOne(){
        return DruidDataSourceBuilder.create().build();
    }
    @Bean(name = "dataSourceTransactionManager1")
    @Primary
    public DataSourceTransactionManager dataSourceTransactionManager(){
        return new DataSourceTransactionManager(dataSourceMysqlOne());
    }
    @Bean("sqlSessionFactory1")
    @Primary
    public SqlSessionFactory sqlSessionFactory(@Qualifier("mysql1DataSource") DataSource dataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(DataSourceConfig1.MAPPER_LOCATION));
        return sessionFactoryBean.getObject();

    }
}
