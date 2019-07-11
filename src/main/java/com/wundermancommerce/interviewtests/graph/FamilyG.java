package com.wundermancommerce.interviewtests.graph;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * Simple Java program to read CSV file in Java. In this program we will read
 * list of people stored in CSV file as comma separated values.
 *
 * @author WINDOWS 8
 */

@SpringBootApplication
public class FamilyG {

    private static final String COMMA_DELIMITER = ",";
//    private List<People> people;
//    private List<Relationship> relationships;

    @Autowired
    private PeopleRepository peopleRepository;

    @Autowired
    private RelationshipRepository relationshipRepository;

    public static void main(String... args) {
        SpringApplication.run(FamilyG.class, args);
    }

    //    @PostConstruct
    public void init() {
//        deleteAll();
        List<People> people = readPeople("src/test/resources/people.csv");
        List<Relationship> relationships = readRelationships("src/test/resources/relationships.csv");
//        deleteAll();
        peopleRepository.save(people);
        relationshipRepository.save(relationships);

        for (People b : people) {
            System.out.println(b);
        }


        for (Relationship r : relationships) {
            System.out.println(r);
        }

    }

    public List<People> getAllPeople() {

        return peopleRepository.findAll();
    }

    public List<Relationship> getAllRelationships() {

        return relationshipRepository.findAll();
    }

    public Long findRelationships(String name) {
        List<People> peopleList = getAllPeople();
        List<Relationship> relationships = getAllRelationships();

        Optional<People> people = peopleList.stream().filter(p -> p.getName().equals(name)).findAny();
        Long relationship = relationships.stream().filter(r -> r.getPerson1().equals(people.get().getEmail())).count();
        Long relationship1 = relationships.stream().filter(r -> r.getPerson2().equals(people.get().getEmail())).count();

        return relationship + relationship1;
    }

    public Long findFamilyMembers(String name) {
        List<People> peopleList = getAllPeople();
        List<Relationship> relationships = getAllRelationships();

        Optional<People> people = peopleList.stream().filter(p -> p.getName().equals(name)).findAny();
        Long familyMembers = relationships.stream().filter(r -> r.getRelationship().equals("FAMILY")).count();
//        Long relationship1 = relationships.stream().filter(r -> r.getPerson2().equals(people.get().getEmail())).count();

        return familyMembers + 1;
    }

    public void deleteAll() {
        peopleRepository.deleteAll();
        relationshipRepository.deleteAll();
    }


//    public People savePeople(People people) {
//        peopleRepository.save(people);
//        return people;
//    }
//
//    public Relationship saveRelationship(Relationship relationship) {
//        relationshipRepository.save(relationship);
//        return relationship;
//    }
//
//    public List<People> getPeople() {
//        Iterable<People> people = peopleRepository.findAll();
//        List<People> target = new ArrayList<>();
//        people.forEach(target::add);
//        return target;
//    }
//
//    public List<Relationship> getRelationship() {
//        Iterable<Relationship> relationship = relationshipRepository.findAll();
//        List<Relationship> target = new ArrayList<>();
//        relationship.forEach(target::add);
//        return target;
//    }

    private List<People> readPeople(String fileName) {
        List<People> peopleList = new ArrayList<>();
        Path pathToFile = Paths.get(fileName);

        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {
            String line = br.readLine();

            while (line != null) {
                String[] attributes = line.split(COMMA_DELIMITER);
                People people = createPeople(attributes);
                peopleList.add(people);
                line = br.readLine();
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return peopleList;
    }

    private List<Relationship> readRelationships(String fileName) {
        List<Relationship> relationshipsList = new ArrayList<>();
        Path pathToFile = Paths.get(fileName);

        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {
            String line = br.readLine();
//            String line =   br.lines().filter(l -> l.matches("(\\d+)(,\\s*\\d+)*"));
            while (line != null) {
                if (line.length() > 0) {
                    String[] attributes = line.split(COMMA_DELIMITER);
                    Relationship relationships = createRelationships(attributes);
                    relationshipsList.add(relationships);

                }
                line = br.readLine();

            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return relationshipsList;
    }

    private People createPeople(String[] metadata) {
        People people = new People();
        people.setName(metadata[0]);
        people.setEmail(metadata[1]);
        people.setAge(Integer.parseInt(metadata[2]));

//        List<Relationship> relationships = new ArrayList<>();
//        relationships.add(metadata[3]);
        return people;
    }

    private Relationship createRelationships(String[] metadata) {
        Relationship relationship = new Relationship();
        relationship.setPerson1(metadata[0]);
        relationship.setRelationship(metadata[1]);
        relationship.setPerson2(metadata[2]);
        return relationship;
    }
}