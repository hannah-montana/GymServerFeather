package io.swagger.repository;

import io.swagger.model.History;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HistoryRepository extends MongoRepository <History, String> {
}
