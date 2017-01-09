package fr.pizzeria.console;

import java.util.Scanner;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import fr.pizzeria.dao.performance.PerformanceDao;
import fr.pizzeria.dao.pizzadao.PizzaDao;
import fr.pizzeria.dao.pizzadao.PizzaDaoJPASpring;

@Configuration
@ComponentScan({"fr.pizzeria.ihm","fr.pizzeria.dao","fr.pizzeria.aspect"})
@EnableJpaRepositories("fr.pizzeria.repos")
@EnableAspectJAutoProxy
public class PizzeriaAppSpringConfig {

	@Bean
	public PlatformTransactionManager transactionManager() {
		JpaTransactionManager txManager = new JpaTransactionManager();
		return txManager;
	}

	@Bean
	public Scanner getScanner(){
		return new Scanner(System.in);
	}
	
	@Bean
	public PizzaDao getPizzaDao(){
		return new PizzaDaoJPASpring();
	}
	
	@Bean
	public PerformanceDao getPerformanceDao(){
		return new PerformanceDao();
	}

	@Bean
	public DataSource getDataSource(){
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://phpmyadmin.dev/pizzeria?useSSL=false");
		dataSource.setUsername("root");
		dataSource.setPassword("");
		return dataSource;
	}

	/*@Bean
	public EmbeddedDatabase getEmbeddedDatabase(){
		EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
		EmbeddedDatabase db = builder.setType(EmbeddedDatabaseType.H2).addScript("schema.sql").addScript("test-data.sql").build();
		return db;
	}*/
}
