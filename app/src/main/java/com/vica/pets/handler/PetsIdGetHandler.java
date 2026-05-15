package com.vica.pets.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.body.BodyHandler;
import com.networknt.config.Config;

import com.networknt.handler.LightHttpHandler;
import com.networknt.http.HttpMethod;
import com.networknt.http.HttpStatus;
import com.networknt.http.MediaType;
import com.vica.pets.model.Pet;
import com.vica.pets.repository.PetRepository;

import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;
import io.undertow.util.HeaderMap;

import java.util.Deque;
import java.util.Map;
import java.util.Optional;

/**
For more information on how to write business handlers, please check the link below.
https://doc.networknt.com/development/business-handler/rest/
*/
public class PetsIdGetHandler implements LightHttpHandler {

	private final PetRepository repository = PetRepository.getInstance();
    
	private static final ObjectMapper MAPPER = new ObjectMapper();
	
	@Override
	public void handleRequest(HttpServerExchange exchange) throws Exception {

        // Map<String, Deque<String>> queryParameters = exchange.getQueryParameters();
	    String idParam = exchange.getQueryParameters()
	            .get("id")
	            .getFirst();

	    Long id = Long.parseLong(idParam);

	    Optional<Pet> pet = repository.findById(id);
	    exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "application/json");

	    if (pet.isPresent()) {

	        String json = MAPPER.writeValueAsString(pet.get());

	        exchange.setStatusCode(200);
	        exchange.getResponseSender().send(json);

	    } else {

	        exchange.setStatusCode(404);
	        exchange.getResponseSender().send("Pet not found");
	    }
	}
    
	/*@Override
    public void handleRequest(HttpServerExchange exchange) throws Exception {
        // HeaderMap requestHeaders = exchange.getRequestHeaders();
        // Map<String, Deque<String>> queryParameters = exchange.getQueryParameters();
        String responseBody = "{}";
        exchange.getResponseHeaders().add(Headers.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        exchange.setStatusCode(HttpStatus.OK.value());
        exchange.getResponseSender().send(responseBody);
        
        repository.findById(id)
        .ifPresent(p -> {
            // use pet
        });
    }*/
}
