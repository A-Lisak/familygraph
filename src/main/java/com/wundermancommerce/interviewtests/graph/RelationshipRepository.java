package com.wundermancommerce.interviewtests.graph;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public interface RelationshipRepository extends CrudRepository<Relationship, Long> {
}
