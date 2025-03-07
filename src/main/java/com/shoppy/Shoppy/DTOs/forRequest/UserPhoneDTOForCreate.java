package com.shoppy.Shoppy.DTOs.forRequest;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.shoppy.Shoppy.entity.UserPhone;
import com.shoppy.Shoppy.entity.Users;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserPhoneDTOForCreate {

    @NotNull
    @Size(max = 10)
    private String phone;

    @NotNull
    @JsonProperty("isPrimary")
    private Boolean isPrimary;

    @NotNull
    private Integer user;

    @NotNull
    private Integer createdBy;

    private Integer updatedBy;

    private Integer deletedBy;

    public static UserPhone mapToEntity(final UserPhoneDTOForCreate userPhoneDTO, final UserPhone userPhone) {
        userPhone.setPhone(userPhoneDTO.getPhone());
        userPhone.setIsPrimary(userPhoneDTO.getIsPrimary());
        final Users user =userPhoneDTO.getUser()!=null?new Users(userPhoneDTO.getUser()):null;
        userPhone.setUser(user);
        final Users createdBy =userPhoneDTO.getCreatedBy()!=null?new Users(userPhoneDTO.getCreatedBy()):null;
        userPhone.setCreatedBy(createdBy);
        final Users updatedBy = userPhoneDTO.getUpdatedBy()!=null?new Users(userPhoneDTO.getUpdatedBy()):null;
        userPhone.setUpdatedBy(updatedBy);
        final Users deletedBy = userPhoneDTO.getDeletedBy()!=null?new Users(userPhoneDTO.getDeletedBy()):null;
        userPhone.setDeletedBy(deletedBy);
        return userPhone;
    }

}
