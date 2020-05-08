package com.example.demosecurityjwt.service;

import com.example.demosecurityjwt.entity.User;

public interface EcomUserService {

    public User createUser(User user);

    public User signIn(User user);

    public User updateUser(User user);

    public void deleteUser(User user);

    public void deleteUserById(Long id);

    public Iterable<?> findAllUsers();

    public Boolean findUserById(Long id);

    public User findUserDetailById(Long id);

    public User changePassword(User user);

    public User changeUserNameOrEmailId(User user);

    public User findByEmailId(String emailId);

    public User findByUserName(String userName);

    public Boolean findByUserNameOrEmail(String userName, String emailId);

    public User findByUserNameOrEmailId(String userName, String emailId);

    public User findByUserNameOrEmailId(String userNameEmailId);

    public Boolean validateUserNameAndEmail(User user);


}
