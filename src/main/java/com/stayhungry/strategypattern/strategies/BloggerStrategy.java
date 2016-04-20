package com.stayhungry.strategypattern.strategies;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.amazonaws.util.json.JSONException;
import com.amazonaws.util.json.JSONObject;

/**
 * This implementation of DecisioningStrategy defines 
 * the decisioning algorithm for blogger requests.
 * 
 * @author dcarrillo
 *
 */
public class BloggerStrategy implements DecisioningStrategy {
	private static final Logger log = Logger.getLogger(BloggerStrategy.class);
	private final Map<String, String> usernames; 
	
	public BloggerStrategy(){
		// Holds the usernames that have been created during this session.
		usernames = new HashMap<String, String>();
	}
	
	/**
	 * Implement the algorithm for making decisions as to whether
	 * a new blogger account can be created.  
	 */
	@Override
	public JSONObject decide(JSONObject payload) {
		JSONObject decision = new JSONObject();
		
		boolean isCreated = false;
		try {
			String user = (String) payload.get("username");
			String msg = "already exists";
			
			// as long as the username doesn't exist, that's good,
			// enough for us; let the account be created.
			if (usernames.get(user) == null){
				usernames.put(user, payload.getString("password"));
				isCreated = true;
				msg = "created";
			}
			
			decision.put("approved", isCreated);
			decision.put("message", String.format("Blog account %s for %s", msg, user));
			
		} catch (JSONException e) {
			log.error(e.getMessage(), e);
		}
		return decision;
	}
}