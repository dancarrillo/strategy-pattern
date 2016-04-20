package com.stayhungry.strategypattern.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import com.stayhungry.strategypattern.ServiceHelper;
import com.stayhungry.strategypattern.strategies.BloggerStrategy;
import com.stayhungry.strategypattern.strategies.DecisioningStrategy;
import com.stayhungry.strategypattern.strategies.LoanStrategy;

@Configuration
@EnableAsync
public class BeanConfig {
	@Bean(name = "bloggerStrategy")
	public DecisioningStrategy getBloggerStrategy() {
		DecisioningStrategy strategy = new BloggerStrategy();
		return strategy;
	}
	@Bean(name = "blogger")
	public ServiceHelper getBloggerServiceHelper() {
		ServiceHelper helper = new ServiceHelper("https://services/bloggers/", getBloggerStrategy());
		return helper;
	}
	
	@Bean(name = "loanStrategy")
	public DecisioningStrategy getLoanStrategy() {
		DecisioningStrategy strategy = new LoanStrategy();
		return strategy;
	}
	@Bean(name = "loan")
	public ServiceHelper getLoanServiceHelper() {
		ServiceHelper helper = new ServiceHelper("https://services/loans/", getLoanStrategy());
		return helper;
	}
}
