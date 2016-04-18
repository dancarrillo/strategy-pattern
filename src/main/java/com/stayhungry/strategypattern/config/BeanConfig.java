package com.stayhungry.strategypattern.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

//import com.stayhungry.strategypattern.BloggerStrategy;
import com.stayhungry.strategypattern.DecisioningStrategy;
import com.stayhungry.strategypattern.ServiceHelper;

@Configuration
@EnableAsync
public class BeanConfig {
//	@Bean(name = "bloggerStrategy")
//	public DecisioningStrategy getBloggerStrategy() {
//		DecisioningStrategy strategy = new BloggerStrategy();
//		return strategy;
//	}
//	@Bean(name = "blogger")
//	public ServiceHelper getBloggerServiceHelper() {
//		ServiceHelper helper = new ServiceHelper("https://services/bloggers/", getBloggerStrategy());
//		return helper;
//	}
}
