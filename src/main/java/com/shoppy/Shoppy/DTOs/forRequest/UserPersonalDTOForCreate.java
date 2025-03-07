package com.shoppy.Shoppy.DTOs.forRequest;

import com.shoppy.Shoppy.entity.UserPersonal;
import com.shoppy.Shoppy.entity.Users;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
public class UserPersonalDTOForCreate {

    @NotNull
    @Size(max = 20)
    private String firstName;

    @NotNull
    @Size(max = 20)
    private String middleName;

    @NotNull
    @Size(max = 20)
    private String lastName;

    @NotNull
    private LocalDate dob;

    @NotNull
    @Size(max = 20)
    private String education;

    @NotNull
    private Integer user;

    @NotNull
    private Integer createdBy;

    private Integer updatedBy;

    private Integer deletedBy;

    public static UserPersonal mapToEntity(final UserPersonalDTOForCreate userPersonalDTO, final UserPersonal userPersonal){
        userPersonal.setFirstName(userPersonalDTO.getFirstName());
        userPersonal.setMiddleName(userPersonalDTO.getMiddleName());
        userPersonal.setLastName(userPersonalDTO.getLastName());
        userPersonal.setDob(userPersonalDTO.getDob());
        userPersonal.setEducation(userPersonalDTO.getEducation());
        final Users user = userPersonalDTO.getUser()!=null?new Users(userPersonalDTO.getUser()):null;
        userPersonal.setUser(user);
        final Users createdBy =userPersonalDTO.getCreatedBy()!=null?new Users(userPersonalDTO.getCreatedBy()):null;
        userPersonal.setCreatedBy(createdBy);
        final Users updatedBy = userPersonalDTO.getUpdatedBy()!=null?new Users(userPersonalDTO.getUpdatedBy()):null;
        userPersonal.setUpdatedBy(updatedBy);
        final Users deletedBy =userPersonalDTO.getDeletedBy()!=null?new Users(userPersonalDTO.getDeletedBy()):null;
        userPersonal.setDeletedBy(deletedBy);
        return userPersonal;
    }
}
