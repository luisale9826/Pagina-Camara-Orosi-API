package com.camaraturismoorosi.apicamaraturismoorosi.user;

import java.util.List;

public interface UserDao {

    void insertUser(User user) throws Exception;

    void updateUser(User user) throws Exception;

    void deleteUser(String userId) throws Exception;

    List<User>getUsers();
    
}
