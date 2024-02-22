package org.bianrylogicit.mandodbdemo.service;

import org.bianrylogicit.mandodbdemo.model.Expense;
import org.springframework.stereotype.Service;
import org.bianrylogicit.mandodbdemo.repository.ExpenseRepository;

import java.util.List;
import java.util.SplittableRandom;

@Service
public class ExpenseService {
    private final ExpenseRepository repository;

    public ExpenseService(ExpenseRepository repository) {
        this.repository = repository;
    }

    public Expense addExpense(Expense expense){
        repository.insert(expense);
        return expense;
    }
    public void updateExpense(String id, Expense expense){
        Expense dbExpense = getExpenseById(id);

        dbExpense.setExpenseName(expense.getExpenseName());
        dbExpense.setType(expense.getType());
        dbExpense.setAmount(expense.getAmount());
        dbExpense.setImageUrl(expense.getImageUrl());

        repository.save(dbExpense);


    }
    public void removeExpense(String id){
        repository.deleteById(id);
    }

    public Expense getExpenseById(String id){
        return repository.findById(id).orElseThrow(()-> new IllegalStateException("No such expense"));
    }

    public List<Expense> getAllExpenses() {
        return repository.findAll();
    }

    public Expense getExpenseByName(String name) {
        return  repository.getExpenseByName(name).orElseThrow(()-> new IllegalStateException("NoSuch Expense found."));
    }
}
