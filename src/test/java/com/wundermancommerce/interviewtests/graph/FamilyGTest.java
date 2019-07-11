package com.wundermancommerce.interviewtests.graph;

import org.assertj.core.api.Assertions;
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


    @Test
    public void contextLoads() {
//        familyG.deleteAll();
        familyG.init();
        List<People> allPeople = familyG.getAllPeople();
        List<Relationship> relationships = familyG.getAllRelationships();

        assertThat(allPeople.size()).isEqualTo(12);
//        assertThat(allPeople.stream().filter(p -> p.getName().equals("bob")).)
//        assertThat(acceptResponse.getAcceptResult()).isNotNull();
//        Assertions.assertThat(acceptResponse.getAcceptResult().getAcceptInitiateMtaResponseItem()).isNotNull();
//        Assertions.assertThat(acceptResponse.getAcceptResult().getAcceptInitiateMtaResponseItem().isSuccess()).isEqualTo(true);
    }

    @Test
    public void findRelationships() {
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
    public void findFamilyMembers() {
        familyG.init();
        Long bob = familyG.findFamilyMembers("Bob");
        Long jenny = familyG.findRelationships("Jenny");
        Long nigel = familyG.findRelationships("Nigel");
        Long alan = familyG.findRelationships("Alan");

        assertThat(bob).isEqualTo(4);
        assertThat(jenny).isEqualTo(3);
        assertThat(nigel).isEqualTo(2);
        assertThat(alan).isEqualTo(0);
    }
}


