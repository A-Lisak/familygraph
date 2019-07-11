package com.wundermancommerce.interviewtests.graph.controllers;

import com.wundermancommerce.interviewtests.graph.People;
import com.wundermancommerce.interviewtests.graph.PeopleRepository;
import com.wundermancommerce.interviewtests.graph.Relationship;
import com.wundermancommerce.interviewtests.graph.RelationshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Validated

public class controller {
    @Autowired
    private PeopleRepository peopleRepository;

    @Autowired
    private RelationshipRepository relationshipRepository;


//to check database, was going to use http://localhost:8080/h2-console but cannot get it working, so
//using postman.


    //http://localhost:8080/people
    @PostMapping("/people")
    public People createPeople(@RequestBody People people) {
        peopleRepository.save(people);
        return people;
    }

    //http://localhost:8080/people/1
    @GetMapping(value = "/people/{id}")
    public People getPeople(@PathVariable final Long id) {
        return peopleRepository.findOne(id);
    }

    //http://localhost:8080/people
    @GetMapping(value = "/people")
    public List<People> getPeople() {
        Iterable<People> response = peopleRepository.findAll();
        List<People> target = new ArrayList<>();
        response.forEach(target::add);
        return target;
    }


    @PostMapping("/relationship")
    public Relationship createRelationship(@RequestBody Relationship relationship) {
        relationshipRepository.save(relationship);
        return relationship;
    }

    @GetMapping(value = "/relationship/{id}")
    public Relationship getRelationship(@PathVariable final Long id) {
        return relationshipRepository.findOne(id);
    }

    @GetMapping(value = "/relationship")
    public List<Relationship> getRelationship() {
        Iterable<Relationship> relationships = relationshipRepository.findAll();
        List<Relationship> target = new ArrayList<>();
        relationships.forEach(target::add);
        return target;
    }
}

