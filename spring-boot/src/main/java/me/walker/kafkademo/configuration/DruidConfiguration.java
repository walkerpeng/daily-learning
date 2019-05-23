package me.walker.kafkademo.configuration;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * alibaba 连接池设置
 */
@Configuration
@ConfigurationProperties(prefix = "spring.datasource")
@Slf4j
@Getter
@Setter
public class DruidConfiguration {
    private String url;
    private String username;
    private String password;
    private String driverClassName;
    private int maxActive;
    private int initialSize;
    private int minIdle;
    private int maxIdle;
    private int maxWait;
    private boolean testWhileIdle;
    private boolean testOnBorrow;
    private boolean testOnReturn;
    private boolean poolPreparedStatements;
    private String validationQuery;
    private int timeBetweenEvictionRunMills;
    private int minEvictableIdleTimeMillis;

    private boolean poolPingEnabled;
    private String poolPingQuery;
    private int poolPingConnectionsNotUsedFor;


    @Bean(name = "druidDataSource")
    @Primary
    public DataSource druidDataSource(){
        DruidDataSource ds = new DruidDataSource();
        ds.setDriverClassName(this.driverClassName);
        ds.setUrl(this.url);
        ds.setUsername(this.username);
        ds.setPassword(this.password);
        ds.setMinIdle(this.minIdle);
        ds.setMaxActive(this.maxActive);
        ds.setInitialSize(this.initialSize);
        ds.setMaxWait(this.maxWait);
        ds.setMinEvictableIdleTimeMillis(this.minEvictableIdleTimeMillis);
        ds.setValidationQuery(this.validationQuery);
        ds.setTestWhileIdle(this.testWhileIdle);
        ds.setTestOnBorrow(this.testOnBorrow);
        ds.setTestOnReturn(this.testOnReturn);
        ds.setPoolPreparedStatements(this.poolPreparedStatements);
        log.info("****************successfully init MySQL connection********************");
        return ds;
    }

    @Bean
    @Primary
    @Autowired
    public SqlSessionFactory sqlSessionFactoryBean(DataSource druidDataSource) throws Exception{
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(druidDataSource);

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:mapper/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    @Primary
    @Autowired
    public PlatformTransactionManager transactionManager(DataSource druiDataSource) throws Exception{
        return new DataSourceTransactionManager(druiDataSource);
    }
}
