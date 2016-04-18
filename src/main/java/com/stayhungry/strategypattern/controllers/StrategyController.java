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

@RestController
public class StrategyController {
	private static final Logger log = Logger.getLogger(StrategyController.class);
	private final Map<String, ServiceHelper> serviceHelpers;
	
	@Inject
	public StrategyController(Map<String, ServiceHelper> serviceHelpers){
		this.serviceHelpers = serviceHelpers;
	}
	
	@RequestMapping(value = "/transactions", method = RequestMethod.POST, consumes={MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> uploadImage(@RequestBody String payload) throws JSONException{
		JSONObject request = new JSONObject(payload);
		log.info("Received input request: " + request.toString());
		ServiceHelper serviceHelper = (ServiceHelper) serviceHelpers.get(request.get("type"));
		log.info("Posting to " + serviceHelper.getUrl());
		
		JSONObject response = new JSONObject();
		return new ResponseEntity<String>(response.toString(), HttpStatus.OK);
		
	}
}
