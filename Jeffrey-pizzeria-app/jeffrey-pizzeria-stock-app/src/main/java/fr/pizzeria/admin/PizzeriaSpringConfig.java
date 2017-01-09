package fr.pizzeria.admin;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import fr.pizzeria.dao.ingredients.IngredientDao;
import fr.pizzeria.dao.performance.PerformanceDao;
import fr.pizzeria.dao.pizzadao.PizzaDao;
import fr.pizzeria.dao.pizzadao.PizzaDaoJPASpring;

@Configuration
@EnableWebMvc
@ComponentScan({"fr.pizzeria.admin.mvc.controller","fr.pizzeria.dao","fr.pizzeria.dao.ingredients","fr.pizzeria.aspect"})
@EnableJpaRepositories("fr.pizzeria.repos")
@EnableAspectJAutoProxy
public class PizzeriaSpringConfig {
	
	@Bean
    public InternalResourceViewResolver internalResourceViewResolver() {
        InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
        internalResourceViewResolver.setPrefix("/WEB-INF/views/");
        internalResourceViewResolver.setSuffix(".jsp");
        return internalResourceViewResolver;
    }


	@Bean
	public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
		JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
		jpaTransactionManager.setEntityManagerFactory(emf);
		return jpaTransactionManager;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
		LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactory.setDataSource(dataSource);
		entityManagerFactory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		entityManagerFactory.setJpaDialect(new HibernateJpaDialect());
		entityManagerFactory.setPackagesToScan("fr.pizzeria.model");

		entityManagerFactory.setJpaPropertyMap(hibernateJpaProperties());
		return entityManagerFactory;
	}

	private Map<String, ?> hibernateJpaProperties() {
		HashMap<String, String> properties = new HashMap<>();
		/*properties.put("hibernate.hbm2ddl.auto", "create");
		properties.put("hibernate.show_sql", "false");
		properties.put("hibernate.format_sql", "false");
		properties.put("hibernate.hbm2ddl.import_files", "insert-data.sql");
		properties.put("hibernate.ejb.naming_strategy", "org.hibernate.cfg.ImprovedNamingStrategy");*/
		

		properties.put("hibernate.c3p0.min_size", "2");
		properties.put("hibernate.c3p0.max_size", "5");
		properties.put("hibernate.c3p0.timeout", "300"); // 5mins

		return properties;
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
	public IngredientDao getIngredientDao(){
		return new IngredientDao();
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

}
