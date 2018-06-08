package com.golaxy;

import java.util.Map;

import javax.persistence.EntityManagerFactory;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(

		entityManagerFactoryRef = "mysqlFundsTaskEntityManagerFactory",

		transactionManagerRef = "mysqlFundsTaskTransactionManager",

		basePackages = { "golaxy.temp" }) // 设置Repository所在位置
public class CMysqlFundsTaskDataSourceConfig {

	@Autowired
	private JpaProperties jpaProperties;

	@Autowired
	@Qualifier("mysqlFundsTaskDataSource")
	private DataSource dataSource;

	/**
	 * 我们通过LocalContainerEntityManagerFactoryBean来获取EntityManagerFactory实例
	 *
	 * @return
	 */
	@Bean(name = "mysqlFundsTaskEntityManagerFactoryBean")
	public LocalContainerEntityManagerFactoryBean mysqlEntityManagerFactoryBean(EntityManagerFactoryBuilder builder) {
		return builder.dataSource(dataSource).properties(getVendorProperties(dataSource)).packages("golaxy.temp.mysql") // 设置实体类所在位置
				.persistenceUnit("FundsTask").build();
	}

	private Map<String, String> getVendorProperties(DataSource dataSource) {
		return jpaProperties.getHibernateProperties(dataSource);
	}

	/**
	 * EntityManagerFactory类似于Hibernate的SessionFactory,mybatis的SqlSessionFactory
	 * 总之,在执行操作之前,我们总要获取一个EntityManager,这就类似于Hibernate的Session,
	 * mybatis的sqlSession.
	 *
	 * @param builder
	 * @return
	 */
	@Bean(name = "mysqlFundsTaskEntityManagerFactory")
	@Primary
	public EntityManagerFactory mysqlEntityManagerFactory(EntityManagerFactoryBuilder builder) {
		return mysqlEntityManagerFactoryBean(builder).getObject();
	}

	/**
	 * 配置事物管理器
	 *
	 * @return
	 */
	@Bean(name = "mysqlFundsTaskTransactionManager")
	@Primary
	public PlatformTransactionManager writeTransactionManager(EntityManagerFactoryBuilder builder) {
		return new JpaTransactionManager(mysqlEntityManagerFactory(builder));
	}
}
