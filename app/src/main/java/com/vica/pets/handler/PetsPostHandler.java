package com.vica.pets.handler;

import com.networknt.body.BodyHandler;
import com.networknt.config.Config;

import com.networknt.handler.LightHttpHandler;
import com.networknt.http.HttpMethod;
import com.networknt.http.HttpStatus;
import com.networknt.http.MediaType;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;
import io.undertow.util.HeaderMap;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vica.pets.repository.PetRepository;
import com.vica.pets.model.Pet;
import java.util.Deque;
import java.util.Map;

/**
For more information on how to write business handlers, please check the link below.
https://doc.networknt.com/development/business-handler/rest/
*/
public class PetsPostHandler implements LightHttpHandler {

	private static final ObjectMapper MAPPER = new ObjectMapper();
	
	private final PetRepository repository = PetRepository.getInstance();
	
    @Override
    public void handleRequest(HttpServerExchange exchange) throws Exception {
        // HeaderMap requestHeaders = exchange.getRequestHeaders();
        // Map<String, Deque<String>> queryParameters = exchange.getQueryParameters();       
    	
    	//read json body        
    	Object body = exchange.getAttachment(BodyHandler.REQUEST_BODY);
   	    Pet pet = MAPPER.convertValue(body, Pet.class);
        
        Pet savedPet = repository.save(pet);
        String json = MAPPER.writeValueAsString(savedPet);

        exchange.setStatusCode(201);
        exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "application/json");
        exchange.getResponseSender().send(json);
    }
}
