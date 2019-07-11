package com.wundermancommerce.interviewtests.graph;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FamilyG.class)
public class FamilyGTest {

    @Autowired
    private FamilyG familyG;

    @Autowired
    private PeopleRepository peopleRepository;

    @Autowired
    private RelationshipRepository relationshipRepository;

    //run tests one at a time, otherwise database gets overpopulated.

    @Test
    public void load_people() {
        familyG.init();
        List<People> allPeople = familyG.getAllPeople();
        List<Relationship> relationships = familyG.getAllRelationships();
        assertThat(allPeople.size()).isEqualTo(12);
    }

    @Test
    public void find_relationships() {
        familyG.init();
        Long bob = familyG.findRelationships("Bob");
        Long jenny = familyG.findRelationships("Jenny");
        Long nigel = familyG.findRelationships("Nigel");
        Long alan = familyG.findRelationships("Alan");

        assertThat(bob).isEqualTo(4);
        assertThat(jenny).isEqualTo(3);
        assertThat(nigel).isEqualTo(2);
        assertThat(alan).isEqualTo(0);
    }

    @Test
    public void find_family_members_bob() {
        familyG.init();
        People bob = new People();
        bob.setName("Bob");
        bob.setEmail("bob@bob.com");
        Long bobn = familyG.findFamilyMembers(bob);
        assertThat(bobn).isEqualTo(4);
    }

    @Test
    public void find_family_members_jenny() {
        familyG.init();
        People jenny = new People();
        jenny.setName("Jenny");
        jenny.setEmail("jenny@toys.com");
        Long jennyn = familyG.findFamilyMembers(jenny);
        assertThat(jennyn).isEqualTo(4);
    }
}