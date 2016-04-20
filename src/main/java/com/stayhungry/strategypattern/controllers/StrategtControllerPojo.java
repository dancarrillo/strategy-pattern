package com.stayhungry.strategypattern.controllers;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.util.json.JSONException;
import com.amazonaws.util.json.JSONObject;
import com.stayhungry.strategypattern.ServiceHelper;
import com.stayhungry.strategypattern.strategies.BloggerStrategy;
import com.stayhungry.strategypattern.strategies.DecisioningStrategy;
import com.stayhungry.strategypattern.strategies.LoanStrategy;

/**
 * This is a Spring controller for handling requests to the /pojo URI.
 * This version removes the Spring mystery and instead creates the objects 
 * associated with the strategy pattern implementation as Pojos.
 * 
 * Note that Spring is still used for the web service.
 * The @RestController tells Spring to manage this as a singleton controller.  
 * So Spring takes care of instantiating it and injecting its dependencies.
 * 
 * @author dcarrillo
 *
 */
@RestController
public class StrategtControllerPojo {
	private static final Logger log = Logger.getLogger(StrategtControllerPojo.class);
//	private Map<String, ServiceHelper> serviceHelpers;
	private Map<String, DecisioningStrategy> strategies;
	
	public StrategtControllerPojo(){
		// OPTION 1: 
		// Create a Map of strategies to be selected at runtime
		strategies = new HashMap<String, DecisioningStrategy>();
		DecisioningStrategy loanStrategy = new LoanStrategy();
		strategies.put("loan", loanStrategy);
		
		DecisioningStrategy blogStrategy = new BloggerStrategy();
		strategies.put("blogger", blogStrategy);
		
		// OPTION 2:
		// This is a different approach were even the service helpers might vary, e.g. different url's.
		// so we build them and place them in a Map, injecting their respective strategies at config time.
		// This mimics what is done in the Spring config found in BeanConfig.java.
//		serviceHelpers = new HashMap<String, ServiceHelper>();
		
		// build out the service and strategy for handling loans. 
//		DecisioningStrategy loanStrategy = new LoanStrategy();
//		ServiceHelper loanService = new ServiceHelper("https://services/loans/", loanStrategy);
//		serviceHelpers.put("loan", loanService);
//		
//		// build out the service and strategy for handling blog account requests
//		DecisioningStrategy blogStrategy = new BloggerStrategy();
//		ServiceHelper blogService = new ServiceHelper("https://services/bloggers/", blogStrategy);
//		serviceHelpers.put("blog", blogService); 
	}
	
	/**
	 * This is a Spring configured RESTful endpoint for accepting web service requests.
	 * The annotation says "I will handle anything sent to http://<HOST>:<PORT>/pojo; I only accept POST;
	 * and I expect the input payload to be JSON.
	 * 
	 * @param payload The input request in JSON format.
	 * @return A JSON formatted response.
	 * @throws JSONException
	 */
	@RequestMapping(value = "/pojo", method = RequestMethod.POST, consumes={MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> postData(@RequestBody String payload) throws JSONException{
		// take the payload and marshall it into a JSONObject so that we have easy access to the data through getters.
		JSONObject request = new JSONObject(payload);
		log.info("Received input request: " + request.toString());
		
		// Runtime injection of the strategy algorithm.  The strategies are keyed by the type value 
		// on the incoming request.  You can imagine new strategies being dynamically created and put 
		// into the map at runtime, and our code doesn't have to change to handle it.
		String type  = request.getString("type");
		ServiceHelper serviceHelper = new ServiceHelper("https://myservice", strategies.get(type));
		
		// The service helpers are keyed by the type value coming in on the request.  So pull out the appropriate helper.
		// This is for OPTION 2. 
//		ServiceHelper serviceHelper = (ServiceHelper) serviceHelpers.get(request.get("type"));
		
		log.info("Posting to " + serviceHelper.getUrl());
		
		// Now, simply retrieve the algorithm from the helper call its decide method.  It will know
		// how to handle the request and provide the appropriate response, maintaining a clean separation 
		// of concerns.
		JSONObject response = serviceHelper.getDecisioningStrategy().decide(request);
		
		return new ResponseEntity<String>(response.toString(), HttpStatus.OK);
	}
}