package com.stayhungry.strategypattern;

import com.amazonaws.util.json.JSONObject;

public interface DecisioningStrategy {
	String decide(JSONObject payload);
}
