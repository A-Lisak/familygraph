package com.wundermancommerce.interviewtests.graph;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FamilyG.class)
public class FamilyGTest {

    @Autowired
    private FamilyG familyG;

    @Test
    public void savePeople() {
    }

    @Test
    public void saveRelationship() {
    }

    @Test
    public void getPeople() {
//        FamilyG familyG = new FamilyG();
//        familyG.getPeople();
        People people = new People( );
        people.setId(1L);
        people.setName("da");
        people.setEmail("sssss");
        people.setAge(22);
        familyG.savePeople(people);
        List<People> result   = familyG.getPeople();

        Assert.assertEquals(result.get(1),1L);
    }

    @Test
    public void getRelationship() {
    }
}