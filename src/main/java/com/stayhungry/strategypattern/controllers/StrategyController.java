package com.stayhungry.strategypattern.controllers;

import java.util.Map;

import javax.inject.Inject;

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

/**
 * This is a Spring controller for handling requests to the /spring URI.
 * It utilizes the Spring injected resources.
 * 
 * The @RestController tells Spring to manage this as a singleton controller.  
 * So Spring takes care of instantiating it and injecting its dependencies.
 * 
 * @author dcarrillo
 *
 */
@RestController
public class StrategyController {
	private static final Logger log = Logger.getLogger(StrategyController.class);
	private final Map<String, ServiceHelper> serviceHelpers;
	
	/**
	 * This constructor is annotated with @Inject which tells Spring that it's
	 * responsible for injecting the parameters - in this case the service helpers map.
	 * @param serviceHelpers
	 */
	@Inject
	public StrategyController(Map<String, ServiceHelper> serviceHelpers){
		this.serviceHelpers = serviceHelpers;
	}
	
	/**
	 * This is a Spring configured RESTful endpoint for accepting web service requests.
	 * The annotation says "I will handle anything sent to http://<HOST>:<PORT>/spring; I only accept POST;
	 * and I expect the input payload to be JSON.
	 * 
	 * @param payload The input request in JSON format.
	 * @return A JSON formatted response.
	 * @throws JSONException
	 */
	@RequestMapping(value = "/spring", method = RequestMethod.POST, consumes={MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> postData(@RequestBody String payload) throws JSONException{
		JSONObject request = new JSONObject(payload);
		log.info("Received input request: " + request.toString());
		ServiceHelper serviceHelper = (ServiceHelper) serviceHelpers.get(request.get("type"));
		
		log.info("Posting to " + serviceHelper.getUrl());
		
		// do iut
		JSONObject response = serviceHelper.getDecisioningStrategy().decide(request);
		
		return new ResponseEntity<String>(response.toString(), HttpStatus.OK);
	}
}