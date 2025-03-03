package com.shoppy.Shoppy.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;


@Entity
@Table(name = "UserAddresses")
@Getter
@Setter
@AllArgsConstructor
public class UserAddress {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userAddressId;

    @Column(nullable = false, length = 20)
    private String city;

    @Column(nullable = false, length = 20)
    private String state;

    @Column
    private Integer zipcode;

    @Column(nullable = false)
    private LocalDate createdOn;

    @Column
    private LocalDate updatedOn;

    @Column
    private LocalDate deletedOn;

    @NotNull
    @JsonProperty("isPrimary")
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

    public UserAddress() {}

    public UserAddress(Integer userAddressId) {
        this.userAddressId = userAddressId;
    }
}
