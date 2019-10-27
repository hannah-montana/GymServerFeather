package io.swagger.repository;


import io.swagger.model.Session;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessionRepository extends MongoRepository <Session, String> {
    Session findByName(String name);
    Session findById(String id);
    List<Session> findByNameRegex(String name);

    @Query("{$or : [{'name': { $regex: ?0}}]}")
    List<Session> findSessionByRegexString(final String regexString);
}

