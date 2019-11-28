package io.swagger.repository;

import io.swagger.model.ParentHistory;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ParentHistoryRepository extends MongoRepository<ParentHistory, String> {
}
