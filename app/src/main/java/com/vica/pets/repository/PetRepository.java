package com.vica.pets.repository;

import com.vica.pets.model.Pet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class PetRepository {

    private static final PetRepository INSTANCE = new PetRepository();
        
    private final Map<Long, Pet> pets = new ConcurrentHashMap<>();
    private final AtomicLong nextId = new AtomicLong(1);

    private PetRepository() {

        Pet dog = new Pet();
        dog.setName("Bella");
        dog.setType("Dog");

        Pet cat = new Pet();
        cat.setName("Whiskers");
        cat.setType("Cat");

        save(dog);
        save(cat);
    }

    public static PetRepository getInstance() {
        return INSTANCE;
    }    

    public Optional<Pet> findById(Long id) {
        return Optional.ofNullable( pets.get(id) );
    }
    
    public Pet save(Pet pet) {

        long id = nextId.getAndIncrement();
   	    pet.setId(id);
    	pets.put(id, pet);
    	return pet;
    }

    // returns true if was found and deleted, false if not found and thus there was nothing to delete
    public boolean deleteById(Long id) {
        return pets.remove(id) != null;
    }
}