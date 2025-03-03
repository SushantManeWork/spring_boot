package com.shoppy.Shoppy.DTOs.forDisplay;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.shoppy.Shoppy.entity.UserPhone;
import com.shoppy.Shoppy.entity.Users;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserPhoneDTOForDisplay {

    private Integer userPhoneId;

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


    public static UserPhoneDTOForDisplay mapToDTO(final UserPhone userPhone) {
        UserPhoneDTOForDisplay userPhoneDTO=new UserPhoneDTOForDisplay();
        userPhoneDTO.setUserPhoneId(userPhone.getUserPhoneId());
        userPhoneDTO.setPhone(userPhone.getPhone());
        userPhoneDTO.setIsPrimary(userPhone.getIsPrimary());
        userPhoneDTO.setUser(userPhone.getUser() == null ? null : userPhone.getUser().getUserId());
        userPhoneDTO.setCreatedBy(userPhone.getCreatedBy() == null ? null : userPhone.getCreatedBy().getUserId());
        userPhoneDTO.setUpdatedBy(userPhone.getUpdatedBy() == null ? null : userPhone.getUpdatedBy().getUserId());
        return userPhoneDTO;
    }

}
