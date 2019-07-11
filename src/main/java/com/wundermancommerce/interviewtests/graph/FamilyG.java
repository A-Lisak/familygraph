package com.wundermancommerce.interviewtests.graph;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.lang.Long.valueOf;

/**
 * Simple Java program to read CSV file in Java. In this program we will read
 * list of people stored in CSV file as comma separated values.
 *
 * @author WINDOWS 8
 */

@SpringBootApplication
public class FamilyG {

    private static final String COMMA_DELIMITER = ",";

    @Autowired
    private PeopleRepository peopleRepository;

    @Autowired
    private RelationshipRepository relationshipRepository;

    public static void main(String... args) {
        SpringApplication.run(FamilyG.class, args);
    }

    public void init() {
        List<People> people = readPeople("src/test/resources/people.csv");
        List<Relationship> relationships = readRelationships("src/test/resources/relationships.csv");
        peopleRepository.save(people);
        relationshipRepository.save(relationships);
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

    public Long findFamilyMembers(People people) {
        List<Relationship> relationshipList = getAllRelationships();
        List<Relationship> person = relationshipList.stream().filter(r -> r.getRelationship().equals("FAMILY"))
                .filter(r -> r.getPerson1().equals(people.getEmail())).collect(Collectors.toList());
        return valueOf(person.size() );
    }

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