package com.example.demosecurityjwt.repository;

import com.example.demosecurityjwt.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String userName);
    User findByEmailId(String emailId);

    @Query("SELECT u FROM User u WHERE u.userName = :userName OR u.emailId = :emailId")
    User findByUserNameOrEmailId(@Param("userName") String userName, @Param("emailId") String emailId);

    @Query("SELECT u.password FROM User u WHERE u.usId = :usId")
    String getPasswordByUserId(@Param("usId") Long usId);

    @Query("SELECT u.password FROM User u WHERE u.userName = :userName")
    String getPasswordByUserName(@Param("userName") String userName);
}
