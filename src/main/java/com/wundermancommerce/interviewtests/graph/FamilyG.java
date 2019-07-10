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
import java.util.ArrayList;
import java.util.List;

/**
 * Simple Java program to read CSV file in Java. In this program we will read
 * list of people stored in CSV file as comma separated values.
 *
 * @author WINDOWS 8
 */
//@SpringBootApplication


//@EntityScan("com.wundermancommerce.interviewtests.graph")
//@EnableJpaRepositories("com.wundermancommerce.interviewtests.graph")
//@SpringBootApplication(scanBasePackages = "ccom.wundermancommerce.interviewtests.graph")

//@EntityScan
//@EnableJpaRepositories
@SpringBootApplication
public class FamilyG {

    private static final String COMMA_DELIMITER = ",";


    @Autowired
    private PeopleRepository peopleRepository;
//    @Autowired
//    private RelationshipRepository relationshipRepository;

//    private List<People> people;
//    private List<Relationship> relationships;

    @PostConstruct
    public void init() {
//        List<People> p = new ArrayList<>();
        List<People> people = readPeople("src/test/resources/people.csv");
//
//        p = people;
//        List<Relationship>   relationships = readRelationships("src/test/resources/relationships.csv");

//        List<People> list = new ArrayList<>();
//        People people = new People();
//
//        people.setName("TeamA");
//        people.setEmail("Callao");
//        list.add(people);
        for (People b : people) {
            System.out.println(b);
        }
        peopleRepository.save(people);


//
//        for (Relationship r : relationships) {
//            System.out.println(r);
//        }

    }

    public List<People> getAllPeople() {

        return peopleRepository.findAll();
    }

//    @Autowired
//    public FamilyG(PeopleRepository peopleRepository, RelationshipRepository relationshipRepository) {
//        this.peopleRepository = peopleRepository;
//        this.relationshipRepository = relationshipRepository;
//    }

//    public FamilyG() {
//    }

    public static void main(String... args) {
        SpringApplication.run(FamilyG.class, args);
//        List<People> people = readPeople("src/test/resources/people.csv");
//        List<Relationship> relationships = readRelationships("src/test/resources/relationships.csv");
//
//        for (People b : people) {
//            System.out.println(b);
//        }
//
//        for (Relationship r : relationships) {
//            System.out.println(r);
//        }
//        dastart();
    }

//    public static void dastart() {
//        List<People> people = readPeople("src/test/resources/people.csv");
//        List<Relationship> relationships = readRelationships("src/test/resources/relationships.csv");
//
//        for (People b : people) {
//            System.out.println(b);
//        }
//
//        for (Relationship r : relationships) {
//            System.out.println(r);
//        }
//    }

//    public  People savePeople(People people) {
//        peopleRepository.save(people);
//        return people;
//    }

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

    private static List<Relationship> readRelationships(String fileName) {
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
//        long id = Integer.parseInt(metadata[0]);
//        String name = metadata[1];
//        String email = metadata[2];
//        int age = Integer.parseInt(metadata[3]);

        People people = new People();
        people.setId(Integer.parseInt(metadata[0]));
        people.setName(metadata[1]);
        people.setEmail(metadata[2]);
        people.setAge(Integer.parseInt(metadata[3]));
        return people;
    }

    private static Relationship createRelationships(String[] metadata) {
        long id = Integer.parseInt(metadata[0]);
        String person1 = metadata[1];
        String relationship = metadata[2];
        String person2 = metadata[3];
        return new Relationship(person1, relationship, person2);
    }
}