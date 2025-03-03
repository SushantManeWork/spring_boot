package com.shoppy.Shoppy.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Entity
@Table(name = "UserPhones")
@Getter
@Setter
@AllArgsConstructor
public class UserPhone {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userPhoneId;

    @Column(nullable = false, length = 10)
    private String phone;

    @Column(nullable = false)
    private LocalDate createdOn;

    @Column
    private LocalDate updatedOn;

    @Column
    private LocalDate deletedOn;

    @Column(nullable = false)
    private Boolean isPrimary;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false)
    private Users user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "createdBy", nullable = false)
    private Users createdBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "updatedBy")
    private Users updatedBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "deletedBy")
    private Users deletedBy;

    public UserPhone() {}
    public UserPhone(Integer userPhoneId) {
        this.userPhoneId = userPhoneId;
    }
}
