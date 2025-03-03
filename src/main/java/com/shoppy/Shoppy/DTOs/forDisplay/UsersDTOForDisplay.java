package com.shoppy.Shoppy.DTOs.forDisplay;

import com.shoppy.Shoppy.entity.Users;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
public class UsersDTOForDisplay {

    private Integer userId;

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

    private List<UserAddressDTOForDisplay> address;

    private UserPersonalDTOForDisplay personal;

    private List<UserPhoneDTOForDisplay> phones;

    private List<UserEmailDTOForDisplay> emails;

    public static UsersDTOForDisplay mapToDTO(final Users users) {
        UsersDTOForDisplay usersDTO=new UsersDTOForDisplay();
        usersDTO.setUserId(users.getUserId());
        usersDTO.setUsername(users.getUsername());
        usersDTO.setPassword(users.getPassword());
        usersDTO.setCreatedBy(users.getCreatedBy());
        usersDTO.setUpdatedBy(users.getUpdatedBy());
        usersDTO.setDeletedBy(users.getDeletedBy());
//        List<String> role1=new ArrayList<>();
//        users.getRoles().forEach(a-> role1.add(a.getRole()));
//        usersDTO.setRoles(role1);
        usersDTO.setAddress(users.getUserUserAddresses()==null?null:users.getUserUserAddresses().stream().map(UserAddressDTOForDisplay::mapToDTO).toList());
        usersDTO.setPersonal(users.getUserUserPersonals()==null?null:UserPersonalDTOForDisplay.mapToDTO(users.getUserUserPersonals()));
        usersDTO.setPhones(users.getUserUserPhones()==null?null:users.getUserUserPhones().stream().map(UserPhoneDTOForDisplay::mapToDTO).toList());
        usersDTO.setEmails(users.getUserUserEmails()==null?null:users.getUserUserEmails().stream().map(UserEmailDTOForDisplay::mapToDTO).toList());
        return usersDTO;
    }
}
