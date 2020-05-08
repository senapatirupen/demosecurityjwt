package com.example.demosecurityjwt.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

@Data
public class AuditLog implements Serializable {
    private static final long serialVersionUID=1L;
    @Column(name="CREATED_BY", nullable = false, unique = false)
    private String createdBy;
    @Column(name="CREATED_DATE", nullable = false, unique = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Column(name="LAST_MODIFIED_BY", nullable = false, unique = false)
    private String lastModifiedBy;
    @Column(name="LAST_MODIFIED_DATE", nullable = false, unique = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;
    @Column(name="IS_ACTIVE", nullable = false, unique = false)
    private Boolean isActive;
    @Column(name="DESC", nullable = false, unique = false)
    private String desc;
    @Column(name="STATUS", nullable = false, unique = false)
    private String status;
}
