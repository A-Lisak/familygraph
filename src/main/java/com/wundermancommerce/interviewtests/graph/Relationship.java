package com.wundermancommerce.interviewtests.graph;

import javax.persistence.*;

@Entity
@Table(name = "relationship")
public class Relationship {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String person1;
    private String relationship;
    private String person2;

    public Relationship() {
    }

    public Relationship(String person1, String relationship, String person2) {
        this.person1 = person1;
        this.relationship = relationship;
        this.person2 = person2;
    }

    public String getPerson1() {
        return person1;
    }

    public void setPerson1(String person1) {
        this.person1 = person1;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getPerson2() {
        return person2;
    }

    public void setPerson2(String person2) {
        this.person2 = person2;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Relationship{" +
                "id=" + id +
                ", person1='" + person1 + '\'' +
                ", relationship='" + relationship + '\'' +
                ", person2='" + person2 + '\'' +
                '}';
    }
}
