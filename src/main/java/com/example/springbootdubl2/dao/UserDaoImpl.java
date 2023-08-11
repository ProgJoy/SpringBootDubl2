package com.example.springbootdubl2.dao;

import com.example.springbootdubl2.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;


import java.util.List;

@Repository
public class UserDaoImpl implements UserDAO {

    @PersistenceContext
    @Autowired
    private EntityManager entityManager;

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Transactional(readOnly = true)
    @Override
    public List<User> listUsers() {
        Query query = entityManager.createQuery("from User", User.class);
        return (List<User>) query.getResultList();
    }

    @Override
    public void editSalary(Long id, int salary) {
        TransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
        TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);
        try {
            entityManager.find(User.class, id).setSalary(salary);
            transactionManager.commit(transactionStatus);
        } catch (Exception ex) {
            transactionManager.rollback(transactionStatus);
        }
    }

    @Transactional
    @Override
    public void createUser(User user) {
        TransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
        TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);
        try {
            entityManager.persist(user);
            transactionManager.commit(transactionStatus);
        } catch (Exception ex) {
            transactionManager.rollback(transactionStatus);
        }
    }

    @Transactional
    @Override
    public void dropUser(Long id) {
        TransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
        TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);
        try {
            entityManager.remove(entityManager.find(User.class, id));
            transactionManager.commit(transactionStatus);
        } catch (Exception ex) {
            transactionManager.rollback(transactionStatus);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public User getUserById(Long id) {
        return entityManager.find(User.class, id);
    }
}