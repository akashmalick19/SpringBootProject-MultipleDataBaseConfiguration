package com.example.demoMultipleDataBaseConfiguration.DataBaseConfigures;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableTransactionManagement
//@EntityScan(basePackages = {"com.example.demoMultipleDataBaseConfiguration.Repository.user"})
@EnableJpaRepositories(entityManagerFactoryRef = "db2EntityManagerFactory",
        transactionManagerRef = "db2TransactionManager",
        basePackages = "com.example.demoMultipleDataBaseConfiguration.Repository.user"
)

public class DbTwoConfig {

    /* DataSource */
    @Bean //** @Bean create Datasource db2DataSource() method object
    @ConfigurationProperties(prefix = "spring.user.datasource")
    public DataSource db2DataSource(){
        return DataSourceBuilder.create().build();
    }

    /* Entity Manager Factory */
    @Bean //** @Bean create EntityManagerFactory db2EntityManagerFactoryBean() method object
    public LocalContainerEntityManagerFactoryBean db2EntityManagerFactory(EntityManagerFactoryBuilder entityManagerFactoryBuilder){
        HashMap<String,Object> hashMap=new HashMap<>();
        hashMap.put("hibernate.ddl-auto","update");
        hashMap.put("hibernate.dialect","org.hibernate.dialect.MySQL8Dialect");
        return entityManagerFactoryBuilder.dataSource(db2DataSource())
                .packages("com.example.demoMultipleDataBaseConfiguration.Model.user")
                .properties(hashMap)
                .build();
    }

    /* Create Transaction Manager */
    @Primary
    @Bean //** @Bean create TransactionManager db2TransactionManager() method object
    public PlatformTransactionManager db2TransactionManager(@Qualifier("db2EntityManagerFactory") EntityManagerFactory entityManagerFactory){
        return new JpaTransactionManager(entityManagerFactory);
    }

}


