package com.shoppy.Shoppy.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Entity
@Table(name = "UserPersonal")
@Getter
@Setter
@AllArgsConstructor
public class UserPersonal {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userPersonalId;

    @Column(nullable = false, length = 20)
    private String firstName;

    @Column(nullable = false, length = 20)
    private String middleName;

    @Column(nullable = false, length = 20)
    private String lastName;

    @Column(nullable = false)
    private LocalDate dob;

    @Column(nullable = false, length = 20)
    private String education;

    @Column(nullable = false)
    private LocalDate createdOn;

    @Column
    private LocalDate updatedOn;

    @Column
    private LocalDate deletedOn;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false, unique = true)
    private Users user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "createdBy", nullable = false)
    private Users createdBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "updatedBy")
    private Users updatedBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "deletedByy")
    private Users deletedBy;

    public UserPersonal() {}
    public UserPersonal(Integer userPersonalId) {
        this.userPersonalId = userPersonalId;
    }
}
