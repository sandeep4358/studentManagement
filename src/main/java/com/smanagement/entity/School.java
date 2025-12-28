package com.smanagement.entity;

import com.smanagement.entity.auditor.Auditable;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "schools_mst")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class School extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String schoolId;
    @Column(name = "name")
    private String name;
    @Column(name = "address")
    private String address;
    @Column(name = "description")
    private String description;
    @Column(name = "phone", unique = true)
    private String schoolPhone;
    @Column(name = "email", unique = true)
    private String email;
    @Column(name = "status")
    private String status;
    @Column(name = "owner_name")
    private String ownerName;
    private String password;
}

