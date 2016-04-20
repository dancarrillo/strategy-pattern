package com.stayhungry.strategypattern.strategies;

import org.apache.log4j.Logger;

import com.amazonaws.util.json.JSONException;
import com.amazonaws.util.json.JSONObject;

/**
 * This implementation of DecisioningStrategy defines 
 * the decisioning algorithm for Loan requests.
 * 
 * @author dcarrillo
 *
 */
public class LoanStrategy implements DecisioningStrategy {
	private static final Logger log = Logger.getLogger(LoanStrategy.class);
	
	@Override
	public JSONObject decide(JSONObject payload) {
		JSONObject decision = new JSONObject();
		try {
			boolean isCreditWorthy = false;
			String msg;
			// Apply some super complex decisioning :) 
			double qualifier = Math.random();
			if (qualifier >= .5D && payload.has("ssn")){
				isCreditWorthy = true;
				decision.put("loanAmount", "$500.00");
				msg = String.format("Loan granted for %s %s", payload.getString("fname"), payload.getString("lname"));
			}else{
				msg = "Sorry, you are not credit worthy";
			}
			decision.put("approved", isCreditWorthy);
			decision.put("message", msg);
			
		} catch (JSONException e) {
			log.error(e.getMessage(), e);
		}
		return decision;
	}
}