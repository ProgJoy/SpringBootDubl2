package com.example.springbootdubl2.service;

import com.example.springbootdubl2.model.User;

import java.util.List;

public interface UserService {

    void createUser(User user);
    List<User> listUsers();
    void editSalary(Long id, int salary);
    void dropUser(Long id);
    User getUserById(Long id);

}
