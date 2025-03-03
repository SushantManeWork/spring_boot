package com.shoppy.Shoppy.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "Roles")
@Getter
@Setter
public class Role {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roleId;

    @Column(nullable = false, length = 30)
    private String role;

    @ManyToMany(mappedBy = "roles")
    private List<Users> users;

    public Role(){}
    public Role(Integer roleId){
        this.roleId=roleId;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleId=" + roleId +
                ", role='" + role + '\'' +
                '}';
    }
}
