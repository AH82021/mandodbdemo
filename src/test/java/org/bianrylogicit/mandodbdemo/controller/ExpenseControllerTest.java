package org.bianrylogicit.mandodbdemo.controller;

import org.bianrylogicit.mandodbdemo.model.Expense;
import org.bianrylogicit.mandodbdemo.model.ExpenseType;
import org.bianrylogicit.mandodbdemo.service.ExpenseService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@Testcontainers
class ExpenseControllerTest {

    @Container
    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:latest");

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @Autowired
    protected ExpenseController controller;

    @MockBean
    protected ExpenseService service;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void addExpense() {
        Expense expenseToAdd = new Expense("testid", "testExpense", ExpenseType.GROCERIES,new BigDecimal(3400),"test/img");
        Expense savedExpense = new Expense("testid", "testExpense", ExpenseType.GROCERIES,new BigDecimal(3400),"test/img");// Mocked response from service

        when(service.addExpense(expenseToAdd)).thenReturn(savedExpense);

        ResponseEntity<Expense> response = controller.addExpense(expenseToAdd);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isEqualTo(savedExpense);
    }

    @Test
    void getAllExpenses() {
    }

    @Test
    void updateExpense() {
    }

    @Test
    void removeExpense() {
    }

    @Test
    void getExpenseByName() {
    }
}