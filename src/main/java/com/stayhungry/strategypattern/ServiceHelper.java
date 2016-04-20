package com.stayhungry.strategypattern;

import com.stayhungry.strategypattern.strategies.DecisioningStrategy;

public class ServiceHelper {
	private final String url;
	private final DecisioningStrategy decisioningStrategy;
	
	public ServiceHelper(String url, DecisioningStrategy decisioningStrategy){
		this.url = url;
		this.decisioningStrategy = decisioningStrategy;
	}
	
	public String getUrl() {
		return url;
	}
	public DecisioningStrategy getDecisioningStrategy() {
		return decisioningStrategy;
	}
}
