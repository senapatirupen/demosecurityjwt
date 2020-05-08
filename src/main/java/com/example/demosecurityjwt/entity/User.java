package com.example.demosecurityjwt.entity;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "USER_DETAIL")
@Data
public class User extends AuditLog {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "US_ID", insertable = false, updatable = false, nullable = false)
    private Long usId;
    @Column(name = "FIRST_NAME", unique = false, nullable = false)
    @NotEmpty(message = "First Name is required")
    private String firstName;
    @Column(name = "LAST_NAME", unique = false, nullable = false)
    @NotEmpty(message = "Last Name is required")
    private String lastName;
    @Column(name = "DOB", unique = false, nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull(message = "DOB is required")
    private Date dob;
    @Column(name = "USER_NAME", unique = false, nullable = false)
    @NotEmpty(message = "User Name is required")
    @Length(min = 5, message = "*Your user name must have at least 5 characters")
    private String userName;
    @Column(name = "EMAIL_ID", unique = false, nullable = false)
    @NotEmpty(message = "Email Id is required")
    @Email(message = "*Please provide a valid Email")
    private String emailId;
    @Column(name = "PHONE_NUMBER", unique = false, nullable = false)
    @NotEmpty(message = "Phone Number is required")
    private String phoneNumber;
    @Column(name = "PASSWORD", unique = false, nullable = false)
    @NotEmpty(message = "Password is required")
    @Length(min = 5, message = "*Your password must have at least 5 characters")
    private String password;
    @Column(name = "RE_PASSWORD", unique = false, nullable = false)
    @NotEmpty(message = "rePassword is required")
    @Length(min = 5, message = "*Your password must have at least 5 characters")
    private String rePassword;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "USER_ROLE_TB", joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
    private Set<UserRole> userRoles;
}

