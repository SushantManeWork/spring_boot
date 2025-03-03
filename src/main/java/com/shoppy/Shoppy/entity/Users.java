package com.shoppy.Shoppy.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "Userses")
@Getter
@Setter
public class Users {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column(nullable = false, length = 30)
    private String username;

    @Column(nullable = false, length = 256)
    private String password;

    @Column(nullable = false, length = 20)
    private String createdBy;

    @Column(nullable = false)
    private LocalDate createdOn;

    @Column(length = 20)
    private String updatedBy;

    @Column
    private LocalDate updatedOn;

    @Column(length = 20)
    private String deletedBy;

    @Column
    private LocalDate deletedOn;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    private UserPersonal userUserPersonals;

    @OneToMany(mappedBy = "createdBy")
    private Set<UserPersonal> createdByUserPersonals;

    @OneToMany(mappedBy = "updatedBy")
    private Set<UserPersonal> updatedByUserPersonals;

    @OneToMany(mappedBy = "deletedBy")
    private Set<UserPersonal> deletedByUserPersonals;

    @OneToMany(mappedBy = "user")
    private Set<UserAddress> userUserAddresses;

    @OneToMany(mappedBy = "createdBy")
    private Set<UserAddress> createdByUserAddresses;

    @OneToMany(mappedBy = "updatedBy")
    private Set<UserAddress> updatedByUserAddresses;

    @OneToMany(mappedBy = "deletedBy")
    private Set<UserAddress> deletedByUserAddresses;

    @OneToMany(mappedBy = "user")
    private Set<UserPhone> userUserPhones;

    @OneToMany(mappedBy = "createdBy")
    private Set<UserPhone> createdByUserPhones;

    @OneToMany(mappedBy = "updatedBy")
    private Set<UserPhone> updatedByUserPhones;

    @OneToMany(mappedBy = "deletedBy")
    private Set<UserPhone> deletedByUserPhones;

    @OneToMany(mappedBy = "user")
    private Set<UserEmail> userUserEmails;

    @OneToMany(mappedBy = "createdBy")
    private Set<UserEmail> createdByUserEmails;

    @OneToMany(mappedBy = "updatedBy")
    private Set<UserEmail> updatedByUserEmails;

    @OneToMany(mappedBy = "deletedBy")
    private Set<UserEmail> deletedByUserEmails;

    @OneToMany(mappedBy = "user")
    private List<Orders> orders;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "UsersRoles",
            joinColumns = @JoinColumn(name = "userId"),
            inverseJoinColumns = @JoinColumn(name = "roleId"))
    private List<Role> roles;

    public Users(){}
    public Users(Integer userId) {
        this.userId = userId;
    }
}
