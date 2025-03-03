package com.shoppy.Shoppy.DTOs.forCreate;

import com.shoppy.Shoppy.entity.Role;
import com.shoppy.Shoppy.entity.Users;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
public class UsersDTOForCreate {

    @NotNull
    @Size(max = 30)
    private String username;

    @NotNull
    @Size(max = 256)
    private String password;

    @NotNull
    @Size(max = 20)
    private String createdBy;

    @Size(max = 20)
    private String updatedBy;

    @Size(max = 20)
    private String deletedBy;

    private List<Integer> roles;

    public static Users mapToEntity(final UsersDTOForCreate usersDTO, final Users users){
        users.setUsername(usersDTO.getUsername());
        users.setCreatedBy(usersDTO.getCreatedBy());
        users.setUpdatedBy(usersDTO.getUpdatedBy());
        users.setDeletedBy(usersDTO.getDeletedBy());
        List<Role> role1=new ArrayList<>();
        usersDTO.getRoles().forEach(a-> role1.add(new Role(a)));
        users.setRoles(role1);
        return users;
    }
}
