package de.lkornelsen.gardening.plants.server.common;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages = { "de.lkornelsen.gardening.plants.server" })
@EnableTransactionManagement
public class ServerConfig {

	@Bean
	public DataSource h2DataSource() {
		DriverManagerDataSource retValue = new DriverManagerDataSource();
		retValue.setDriverClassName("org.h2.Driver");
		retValue.setUrl("jdbc:h2:c:/development/data/h2/plantsdb");
		retValue.setUsername("sa");

		return retValue;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean retValue = new LocalContainerEntityManagerFactoryBean();
		retValue.setPersistenceUnitName("plantsHsql");

		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		retValue.setJpaVendorAdapter(vendorAdapter);

		Properties jpaProperties = new Properties();
		jpaProperties.put("hibernate.archive.autodetection", "class");
		jpaProperties.put("hibernate.format_sql", "true");
		jpaProperties.put("hibernate.show_sql", "true");
		jpaProperties.put("hibernate.hbm2ddl.auto", "update");
		jpaProperties.put("hibernate.dialect",
				"org.hibernate.dialect.H2Dialect");
		jpaProperties.put("hibernate.format_sql", "true");

		retValue.setJpaProperties(jpaProperties);

		retValue.setPackagesToScan("de.lkornelsen.gardening.plants");

		retValue.setDataSource(h2DataSource());

		return retValue;
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		JpaTransactionManager retValue = new JpaTransactionManager();
		retValue.setEntityManagerFactory(entityManagerFactory().getObject());

		return retValue;
	}
}
