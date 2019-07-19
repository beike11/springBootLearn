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

/**
 * @author stevenw
 * @date 2019/7/15
 */
@Configuration
@MapperScan(basePackages= DataSourceConfig2.PACKAGE, sqlSessionFactoryRef = "sqlSessionFactory2")
public class DataSourceConfig2 {
    static final String PACKAGE = "com.stevenw.mysqldao2";
    static final String MAPPER_LOCATION = "classpath:mapper/mysql2/*.xml";

    @Bean(name = "mysql2DataSource")
    @ConfigurationProperties("spring.datasource.druid.mysql2")
    public DataSource dataSourceMysqlOne(){
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = "dataSourceTransactionManager2")
    public DataSourceTransactionManager dataSourceTransactionManager(){
        return new DataSourceTransactionManager(dataSourceMysqlOne());
    }

    @Bean("sqlSessionFactory2")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("mysql2DataSource") DataSource dataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(DataSourceConfig2.MAPPER_LOCATION));
        return sessionFactoryBean.getObject();

    }
}
