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
//@EntityScan(basePackages = {"com.example.demoMultipleDataBaseConfiguration.Repository.employee"})
@EnableJpaRepositories(entityManagerFactoryRef = "db1EntityManagerFactory",
    transactionManagerRef = "db1TransactionManager",
        basePackages = "com.example.demoMultipleDataBaseConfiguration.Repository.employee"
)

public class DbOneConfig {
    /* Create DataSource Object */
    @Primary
    @Bean //** @Bean creates Datasource db1DataSource() method object
    @ConfigurationProperties(prefix = "spring.employee.datasource") // it configures the property of db1.datasource which is defines inside application-properties
    public DataSource db1DataSource(){
        return DataSourceBuilder.create().build();
    }

    /* Entity Manager Factory */
    @Primary
    @Bean //** @Bean creates EntityManagerFactory db1EntityManagerFactoryBean() method object
    public LocalContainerEntityManagerFactoryBean db1EntityManagerFactory(EntityManagerFactoryBuilder entityManagerFactoryBuilder){
        HashMap<String,Object> hashMap=new HashMap<>();
        hashMap.put("hibernate.ddl-auto","update");
        hashMap.put("hibernate.dialect","org.hibernate.dialect.MySQL8Dialect");
        return entityManagerFactoryBuilder.dataSource(db1DataSource())
                .packages("com.example.demoMultipleDataBaseConfiguration.Model.employee")
                .properties(hashMap)
                .build();
    }

    /* Create Transaction Manager */
    @Primary
    @Bean //** @Bean creates TransactionManager db1TransactionManager() method object
    public PlatformTransactionManager db1TransactionManager(@Qualifier("db1EntityManagerFactory") EntityManagerFactory entityManagerFactory){
        return new JpaTransactionManager(entityManagerFactory);
    }

}

