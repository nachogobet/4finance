package io.fourfinanceit.homework.config;

import javax.persistence.EntityManager;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.orm.jpa.EntityManagerFactoryInfo;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
public class Config 
{
	@Bean
	SimpleDriverDataSource getDataSource() {
		SimpleDriverDataSource bean = new SimpleDriverDataSource(new org.postgresql.Driver(), "jdbc:postgresql://localhost:5432/4finance", "postgres", "admin");
				
		return bean;
	}
	
	@Bean
	EntityManager getEntityManager()
	{
		return (EntityManager)getEntityManagerFactoryInfo().getNativeEntityManagerFactory().createEntityManager();
	}

	@Bean
	EntityManagerFactoryInfo getEntityManagerFactoryInfo() 
	{
		LocalContainerEntityManagerFactoryBean bean = new org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean();
		bean.setDataSource(getDataSource());
		bean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		bean.setPersistenceXmlLocation("classpath*:persistence.xml");
		return bean;
	}					
}
