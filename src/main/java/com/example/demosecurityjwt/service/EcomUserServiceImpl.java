package com.example.demosecurityjwt.service;

import com.example.demosecurityjwt.entity.User;
import com.example.demosecurityjwt.entity.UserRole;
import com.example.demosecurityjwt.exception.UserExistException;
import com.example.demosecurityjwt.exception.UserNotAuthorizedException;
import com.example.demosecurityjwt.exception.UserNotFoundException;
import com.example.demosecurityjwt.repository.UserRepository;
import com.example.demosecurityjwt.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;

@Service
public class EcomUserServiceImpl implements EcomUserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserRoleRepository userRoleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public User createUser(User user) {
        Boolean flag = findByUserNameOrEmail(user.getUserName(), user.getEmailId());
        if(!user.getPassword().equalsIgnoreCase(user.getRePassword())) {
            throw new UserExistException(" Password and RePassword are not equal ");
        }
        if (!flag) {
            String password = passwordEncoder.encode(user.getPassword());
            user.setPassword(password);
            UserRole userRole = new UserRole();
            userRole.setRole("ADMIN_USER");
//        userRoleRepository.save(userRole);
            user.setUserRoles(new HashSet<UserRole>(Arrays.asList(userRole)));
            user.setRePassword("*****");
            user = userRepository.save(user);
        } else {
            throw new UserExistException(" User Name or Email Id already exist ");
        }
        return user;
    }

    @Override
    public User signIn(User user) {
        String givenPassword = user.getPassword();
        String givenUserName = user.getUserName();
        user = findByUserNameOrEmailId(user.getUserName());
        if(Objects.isNull(user)){
            throw new UserNotFoundException(" User not found for {}", givenUserName);
        }
//        matches method signature :: public boolean matches(CharSequence rawPassword, String encodedPassword)
        Boolean flag = passwordEncoder.matches(givenPassword, user.getPassword());
        if (!flag) {
            throw new UserNotAuthorizedException("Password is incorrect");
        }
        return user;
    }

    @Override
    public User updateUser(User user) {
        User oldUser = findUserDetailById(user.getUsId());
        oldUser.setFirstName(StringUtils.isEmpty(user.getFirstName())?oldUser.getFirstName():user.getFirstName());
        oldUser.setLastName(StringUtils.isEmpty(user.getLastName())?oldUser.getLastName():user.getLastName());
        oldUser.setPhoneNumber(StringUtils.isEmpty(user.getPhoneNumber())?oldUser.getPhoneNumber():user.getPhoneNumber());
        oldUser.setDob(StringUtils.isEmpty(user.getDob())?oldUser.getDob():user.getDob());
        userRepository.save(oldUser);
        return oldUser;
    }

    @Override
    public void deleteUser(User user) {
        findUserById(user.getUsId());
        userRepository.delete(user);
    }

    @Override
    public void deleteUserById(Long id) {
        findUserById(id);
        userRepository.deleteById(id);
    }

    @Override
    public Iterable<?> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Boolean findUserById(Long id) {
        if (userRepository.findById(id).isPresent()) {
            return true;
        } else {
            throw new UserNotFoundException(" User Not Found for id : " + id);
        }
    }

    @Override
    public User findUserDetailById(Long id) {
        if (userRepository.findById(id).isPresent()) {
            return userRepository.findById(id).get();
        } else {
            throw new UserNotFoundException(" User Not Found for id : " + id);
        }
    }

    @Override
    public User changePassword(User user) {
        findUserById(user.getUsId());
        if(user.getPassword().equalsIgnoreCase(user.getRePassword())) {
            throw new UserExistException(" Old Password and New Password are equal ");
        }
        String oldPasswordString = userRepository.getPasswordByUserId(user.getUsId());
        Boolean flag = passwordEncoder.matches(user.getRePassword(), oldPasswordString);
        if (flag) {
            throw new UserNotAuthorizedException(" New Password is equal to Old Password from DB ");
        }
        User oldUser = findUserDetailById(user.getUsId());
        String password = passwordEncoder.encode(user.getRePassword());
        oldUser.setPassword(password);
        oldUser = userRepository.save(oldUser);
        return oldUser;
    }

    @Override
    public User changeUserNameOrEmailId(User user) {
        findUserById(user.getUsId());
        findByUserNameOrEmail(user.getUserName(), user.getEmailId());
        User oldUser = findUserDetailById(user.getUsId());
        oldUser.setUserName(StringUtils.isEmpty(user.getUserName())?oldUser.getUserName():user.getUserName());
        oldUser.setEmailId(StringUtils.isEmpty(user.getEmailId())?oldUser.getEmailId():user.getEmailId());
        oldUser = userRepository.save(oldUser);
        return oldUser;
    }

    @Override
    public User findByEmailId(String emailId) {
        User user = userRepository.findByEmailId(emailId);
        if (Objects.isNull(user)) {
            throw new UserNotFoundException(" User Not Found for emailId : " + emailId);
        }
        return user;
    }

    @Override
    public User findByUserName(String userName) {
        User user = userRepository.findByUserName(userName);
        if (Objects.isNull(user)) {
            throw new UserNotFoundException(" User Not Found for username : " + userName);
        }
        return user;
    }

    @Override
    public Boolean findByUserNameOrEmail(String userName, String emailId) {
        User user = userRepository.findByUserName(userName);
        if (!Objects.isNull(user)) {
            throw new UserExistException(" User Name already exist ");
        }
        user = userRepository.findByEmailId(emailId);
        if (!Objects.isNull(user)) {
            throw new UserExistException(" Email already exist ");
        }
        return false;
    }

    @Override
    public User findByUserNameOrEmailId(String userName, String emailId) {
        User user = userRepository.findByUserNameOrEmailId(userName, emailId);
        if (!Objects.isNull(user)) {
            throw new UserExistException(" User Name or Email Id already exist ");
        }
        return user;
    }

    @Override
    public User findByUserNameOrEmailId(String userNameEmailId) {
        String emailId = "";
        String userName = "";
        if (userNameEmailId.contains(".com"))
            emailId = userNameEmailId;
        else
            userName = userNameEmailId;
        User user = userRepository.findByUserNameOrEmailId(userName, emailId);
        return user;
    }

    @Override
    public Boolean validateUserNameAndEmail(User user) {
        User validUser = null;
        Boolean flag = false;
        if (!StringUtils.isEmpty(user.getUserName())) {
            validUser = findByUserName(user.getUserName());
            if (!Objects.isNull(validUser) && !StringUtils.isEmpty(validUser.getUserName())) {
                flag = true;
            }
        }
        if (!StringUtils.isEmpty(user.getEmailId())) {
            validUser = userRepository.findByEmailId(user.getEmailId());
            if (!Objects.isNull(validUser) && !StringUtils.isEmpty(validUser.getEmailId())) {
                flag = true;
            }
        }
        return flag;
    }

}
