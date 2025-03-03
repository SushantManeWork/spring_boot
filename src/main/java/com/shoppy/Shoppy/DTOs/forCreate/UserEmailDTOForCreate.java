package com.shoppy.Shoppy.DTOs.forCreate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.shoppy.Shoppy.entity.UserEmail;
import com.shoppy.Shoppy.entity.Users;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserEmailDTOForCreate {

    @NotNull
    @Size(max = 30)
    private String email;

    @NotNull
    @JsonProperty("isPrimary")
    private Boolean isPrimary;

    @NotNull
    private Integer user;

    @NotNull
    private Integer createdBy;

    private Integer updatedBy;

    private Integer deletedBy;


    public static UserEmail mapToEntity(final UserEmailDTOForCreate userEmailDTO, final UserEmail userEmail) {
        userEmail.setEmail(userEmailDTO.getEmail());
        userEmail.setIsPrimary(userEmailDTO.getIsPrimary());
        final Users user = userEmailDTO.getUser()!=null?new Users(userEmailDTO.getUser()):null;
        userEmail.setUser(user);
        final Users createdBy = userEmailDTO.getCreatedBy()!=null?new Users(userEmailDTO.getCreatedBy()):null;
        userEmail.setCreatedBy(createdBy);
        final Users updatedBy = userEmailDTO.getUpdatedBy()!=null?new Users(userEmailDTO.getUpdatedBy()):null;;
        userEmail.setUpdatedBy(updatedBy);
        final Users deletedBy = userEmailDTO.getDeletedBy()!=null?new Users(userEmailDTO.getDeletedBy()):null;
        userEmail.setDeletedBy(deletedBy);
        return userEmail;
    }
}
