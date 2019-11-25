package io.swagger.repository;

import io.swagger.model.History;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryRepository extends MongoRepository <History, String> {
    History findById(String id);
}
