package com.depa.progettinocovid.util;

import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionManager;

import com.google.common.base.Preconditions;

// configurazione per il db postgres usato per associazione province
@Configuration
public class DBConfig {
	
	@Autowired
    private Environment env;

	@Bean
	public PGSimpleDataSource dataSource() {
		PGSimpleDataSource ds = new PGSimpleDataSource();
		ds.setUrl(Preconditions.checkNotNull(env.getProperty("jdbc.url")));
		ds.setUser(Preconditions.checkNotNull(env.getProperty("jdbc.user")));
		ds.setPassword(Preconditions.checkNotNull(env.getProperty("jdbc.pass")));
		return ds;
	}

	@Bean
	public JdbcTemplate jdbcTemplate() {
		return new JdbcTemplate(dataSource());
	}
	
	@Bean
	public TransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}
}