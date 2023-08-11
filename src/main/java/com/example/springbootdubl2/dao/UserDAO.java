package com.example.springbootdubl2.dao;


import java.util.List;
import com.example.springbootdubl2.model.User;

public interface UserDAO {
    void createUser(User user);
    List<User> listUsers();
    void editSalary(Long id, int salary);
    void dropUser(Long id);
    User getUserById(Long id);
}
