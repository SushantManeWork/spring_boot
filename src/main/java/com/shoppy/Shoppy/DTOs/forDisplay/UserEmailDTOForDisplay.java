package com.shoppy.Shoppy.DTOs.forDisplay;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.shoppy.Shoppy.entity.UserEmail;
import com.shoppy.Shoppy.entity.Users;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserEmailDTOForDisplay {

    private Integer userEmailId;

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


    public static UserEmailDTOForDisplay mapToDTO(final UserEmail userEmail) {
        UserEmailDTOForDisplay userEmailDTO=new UserEmailDTOForDisplay();
        userEmailDTO.setUserEmailId(userEmail.getUserEmailId());
        userEmailDTO.setEmail(userEmail.getEmail());
        userEmailDTO.setIsPrimary(userEmail.getIsPrimary());
        userEmailDTO.setUser(userEmail.getUser() == null ? null : userEmail.getUser().getUserId());
        userEmailDTO.setCreatedBy(userEmail.getCreatedBy() == null ? null : userEmail.getCreatedBy().getUserId());
        userEmailDTO.setUpdatedBy(userEmail.getUpdatedBy() == null ? null : userEmail.getUpdatedBy().getUserId());
        return userEmailDTO;
    }

}
