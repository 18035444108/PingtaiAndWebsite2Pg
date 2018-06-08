package com.golaxy;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.golaxy.service.KeywordRuleServie;
import com.golaxy.service.PingtaiInfoService;
import com.golaxy.service.WebSiteInfoService;
import com.golaxy.utils.FileUtils;
import com.netflix.discovery.converters.jackson.builder.StringInterningAmazonInfoBuilder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(Application.class);
		app.run(args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("application start!");
		
	   String ptid_s = FileUtils.readFile("./conf/ptid.txt").trim();
		int ptid = Integer.parseInt(ptid_s);
		pingtaiInfoService().pingtai_info(ptid);
		webSiteInfoService().weisite_info(ptid);
		keywordRuleServie().ruleCreate(ptid);
		
	}

	@Bean
	public PingtaiInfoService pingtaiInfoService() {
		return new PingtaiInfoService();
	}
	
	@Bean
	public WebSiteInfoService webSiteInfoService(){
		return new WebSiteInfoService();
	}
	@Bean
	public KeywordRuleServie keywordRuleServie(){
		return new KeywordRuleServie();
	}
	
	@Bean
	public ObjectMapper objectMapper() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY, true);
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
		mapper.configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, true);
		mapper.configure(DeserializationFeature.ACCEPT_FLOAT_AS_INT, false);
		return mapper;
	}


//	@Bean(name = "mysqlDataSource")
//	@ConfigurationProperties(prefix = "spring.data.mysql")
//	public DataSource mysqlDataSource() {
//		System.out.println("-------------------- productDataSource init ---------------------");
//		return new org.apache.tomcat.jdbc.pool.DataSource();
//	}

	@Bean(name = "mysqlFundsTaskDataSource")
	@ConfigurationProperties(prefix = "spring.data.mysql.funds_task")
	public DataSource mysqlFundsTaskDataSource() {
		System.out.println("-------------------- mysqlFundsTaskDataSource init ---------------------");
		return new org.apache.tomcat.jdbc.pool.DataSource();
	}
	
	/**
	 * 加载postgres配置
	 *
	 * @return
	 */
	@Bean(name = "postgresDataSource")
	@Primary
	@ConfigurationProperties(prefix = "spring.datasource.pg")
	public DataSource postgresDataSource() {
		return (DataSource) DataSourceBuilder.create().build();
	}
	
}
