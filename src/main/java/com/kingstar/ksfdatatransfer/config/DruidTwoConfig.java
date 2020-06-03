package com.kingstar.ksfdatatransfer.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @Description
 * @Tips
 * @Author yikai.wang
 * @Date 2020/5/13 17:44
 */
@Configuration
@MapperScan(basePackages = {"com.kingstar.ksfdatatransfer.mapper.two"},sqlSessionTemplateRef = "sqlSessionTemplateTwo")
public class DruidTwoConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(DruidTwoConfig.class) ;
    @Resource
    private DruidTwoParam druidTwoParam ;

    @Bean("dataSourceTwo")
    public DataSource dataSourceTwo () {
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(druidTwoParam.getDbUrl());
        datasource.setUsername(druidTwoParam.getUsername());
        datasource.setPassword(druidTwoParam.getPassword());
        datasource.setDriverClassName(druidTwoParam.getDriverClassName());
        datasource.setInitialSize(druidTwoParam.getInitialSize());
        datasource.setMinIdle(druidTwoParam.getMinIdle());
        datasource.setMaxActive(druidTwoParam.getMaxActive());
        datasource.setMaxWait(druidTwoParam.getMaxWait());
        datasource.setTimeBetweenEvictionRunsMillis(druidTwoParam.getTimeBetweenEvictionRunsMillis());
        datasource.setMinEvictableIdleTimeMillis(druidTwoParam.getMinEvictableIdleTimeMillis());
        datasource.setMaxEvictableIdleTimeMillis(druidTwoParam.getMaxEvictableIdleTimeMillis());
        datasource.setValidationQuery(druidTwoParam.getValidationQuery());
        datasource.setTestWhileIdle(druidTwoParam.isTestWhileIdle());
        datasource.setTestOnBorrow(druidTwoParam.isTestOnBorrow());
        datasource.setTestOnReturn(druidTwoParam.isTestOnReturn());
        datasource.setPoolPreparedStatements(druidTwoParam.isPoolPreparedStatements());
        datasource.setMaxPoolPreparedStatementPerConnectionSize(druidTwoParam.getMaxPoolPreparedStatementPerConnectionSize());
        try {
            datasource.setFilters(druidTwoParam.getFilters());
        } catch (Exception e) {
            LOGGER.error("druid configuration initialization filter", e);
        }
        datasource.setConnectionProperties(druidTwoParam.getConnectionProperties());
        return datasource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactoryTwo() throws Exception{
        MybatisSqlSessionFactoryBean factory = new MybatisSqlSessionFactoryBean();
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        factory.setDataSource(dataSourceTwo());
        factory.setMapperLocations(resolver.getResources("classpath*:/dataTwoMapper/*.xml"));
        return factory.getObject();
    }

    @Bean(name="transactionManagerTwo")
    public DataSourceTransactionManager transactionManagerTwo(){
        return  new DataSourceTransactionManager(dataSourceTwo());
    }

    @Bean(name = "sqlSessionTemplateTwo")
    public SqlSessionTemplate sqlSessionTemplateTwo() throws Exception {
        return new SqlSessionTemplate(sqlSessionFactoryTwo());
    }
}
