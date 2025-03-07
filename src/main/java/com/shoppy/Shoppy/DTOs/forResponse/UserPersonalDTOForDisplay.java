package com.shoppy.Shoppy.DTOs.forResponse;

import com.shoppy.Shoppy.entity.UserPersonal;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
public class UserPersonalDTOForDisplay {

    private Integer userPersonalId;

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


    public static UserPersonalDTOForDisplay mapToDTO(final UserPersonal userPersonal){
        UserPersonalDTOForDisplay userPersonalDTO= new UserPersonalDTOForDisplay();
        userPersonalDTO.setUserPersonalId(userPersonal.getUserPersonalId());
        userPersonalDTO.setFirstName(userPersonal.getFirstName());
        userPersonalDTO.setMiddleName(userPersonal.getMiddleName());
        userPersonalDTO.setLastName(userPersonal.getLastName());
        userPersonalDTO.setDob(userPersonal.getDob());
        userPersonalDTO.setEducation(userPersonal.getEducation());
        userPersonalDTO.setUser(userPersonal.getUser() == null ? null : userPersonal.getUser().getUserId());
        userPersonalDTO.setCreatedBy(userPersonal.getCreatedBy() == null ? null : userPersonal.getCreatedBy().getUserId());
        userPersonalDTO.setUpdatedBy(userPersonal.getUpdatedBy() == null ? null : userPersonal.getUpdatedBy().getUserId());
        return userPersonalDTO;
    }

}
