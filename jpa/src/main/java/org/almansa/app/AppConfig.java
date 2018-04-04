package org.almansa.app;

import java.util.Properties;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.almansa.app.service.MessageProvider;
import org.almansa.app.service.MessageProviderImpl;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configurable
@ComponentScan("org.almansa.app")
@EnableTransactionManagement
public class AppConfig{
    
    @Bean
    public MessageProvider messageProvider() {
        return new MessageProviderImpl();
    }
    
    @Bean
    public DataSource dataSource() throws ClassNotFoundException {        
        DriverManagerDataSource  dataSource = new DriverManagerDataSource (); 
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:~/test6");        
        dataSource.setUsername("sa");
        
        return dataSource;
    }
    
    @Bean
    public JpaTransactionManager jpaTransactionManager() throws ClassNotFoundException {  
        JpaTransactionManager jtm = new JpaTransactionManager();
        jtm.setEntityManagerFactory(localContainerEntityManagerFactoryBean().getObject());
        
        return jtm;
    }
    
    @Bean 
    public PersistenceAnnotationBeanPostProcessor persistenceAnnotationBeanPostProcessor() {
        return new PersistenceAnnotationBeanPostProcessor();
    }
    
    @Bean 
    public LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean() throws ClassNotFoundException {
        LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
        entityManager.setDataSource(dataSource());
        entityManager.setPackagesToScan("org.almansa.app");
        
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        entityManager.setJpaVendorAdapter(vendorAdapter);
        entityManager.setJpaProperties(additionalProperties());
        
        return entityManager;
    }   
    
    private Properties additionalProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "create");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        properties.setProperty("hibernate.show_sql", "true");
        properties.setProperty("hibernate.format_sql", "true");
        properties.setProperty("hibernate.id.new_generator_mappings", "true");
        return properties;
     }    
}