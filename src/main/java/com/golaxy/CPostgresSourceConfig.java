package com.golaxy;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.netflix.governator.annotations.binding.Primary;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "postgresEntityManagerFactory", transactionManagerRef = "postgresTransactionManager", basePackages = {
		"com.golaxy.dao.postgres", "com.golaxy.model.postgres" })
public class CPostgresSourceConfig {
	@Autowired
	@Qualifier("postgresDataSource")
	private DataSource dataSource;

	@Autowired
	private JpaProperties jpaProperties;

	@Bean(name = "postgresEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean postgresEntityManagerFactory(EntityManagerFactoryBuilder builder) {
		return builder.dataSource(dataSource).packages("com.golaxy.dao.postgres", "com.golaxy.model.postgres")
				.persistenceUnit("CPostgresSourceConfig").properties(jpaProperties.getProperties()).build();
	}

	@Bean(name = "postgresTransactionManager")
	public PlatformTransactionManager postgresTransactionManager(EntityManagerFactoryBuilder builder) {
		return new JpaTransactionManager(postgresEntityManagerFactory(builder).getObject());
	}
}
