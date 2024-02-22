package org.bianrylogicit.mandodbdemo.repository;

import org.bianrylogicit.mandodbdemo.model.Expense;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

@Repository
public interface ExpenseRepository  extends MongoRepository<Expense,String> {
    @Query("{'name':?0}")
    Optional<Expense> getExpenseByName(String name);
}
