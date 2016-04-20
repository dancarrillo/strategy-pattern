package com.stayhungry.strategypattern.strategies;

import com.amazonaws.util.json.JSONObject;

/**
 * This simple interface defines the contract for decision strategy implementations.
 * @author dcarrillo
 *
 */
public interface DecisioningStrategy {
	JSONObject decide(JSONObject payload);
}
