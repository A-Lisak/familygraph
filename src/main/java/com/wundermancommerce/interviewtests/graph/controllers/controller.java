package com.wundermancommerce.interviewtests.graph.controllers;

import com.wundermancommerce.interviewtests.graph.*;
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

    @PostMapping("/people")
    public People createPeople(@RequestBody People people) {
        peopleRepository.save(people);
        return people;
    }

    @GetMapping(value = "/people/{id}")
    public People getPeople(@PathVariable final Long id) {
        return peopleRepository.findOne(id);
    }

    @GetMapping(value = "/people")
    public List<People> getPeople() {
        Iterable<People> response = peopleRepository.findAll();
        List<People> target = new ArrayList<>();
        response.forEach(target::add);
        return target;
    }

//    @GetMapping(value = "/xpeople")
//    public void getXPeople() {
//        FamilyG familyG = new FamilyG();
//        familyG.init();
//
//    }


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

