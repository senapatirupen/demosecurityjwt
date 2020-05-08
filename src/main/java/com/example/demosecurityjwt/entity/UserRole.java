package com.example.demosecurityjwt.entity;


import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "USER_ROLE")
@Data
public class UserRole extends AuditLog {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "UR_ID")
    private Long urId;
    @Column(name = "ROLE", unique = false, nullable = false)
    private String role;
}
