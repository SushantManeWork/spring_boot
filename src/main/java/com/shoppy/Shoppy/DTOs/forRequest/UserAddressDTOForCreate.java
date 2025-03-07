package com.shoppy.Shoppy.DTOs.forRequest;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.shoppy.Shoppy.entity.UserAddress;
import com.shoppy.Shoppy.entity.Users;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserAddressDTOForCreate {

    @NotNull
    @Size(max = 20)
    private String city;

    @NotNull
    @Size(max = 20)
    private String state;

    private Integer zipcode;

    @NotNull
    @JsonProperty("isPrimary")
    private Boolean isPrimary;

    @NotNull
    private Integer userId;

    @NotNull
    private Integer createdBy;

    private Integer updatedBy;

    private Integer deletedBy;


    public static UserAddress mapToEntity(final UserAddressDTOForCreate userAddressDTO, final UserAddress userAddress) {
        userAddress.setCity(userAddressDTO.getCity());
        userAddress.setState(userAddressDTO.getState());
        userAddress.setZipcode(userAddressDTO.getZipcode());
        final Users user =userAddressDTO.getUserId()!=null?new Users(userAddressDTO.getUserId()):null;
        userAddress.setUser(user);
        final Users createdBy = userAddressDTO.getCreatedBy()!=null?new Users(userAddressDTO.getCreatedBy()):null;
        userAddress.setCreatedBy(createdBy);
        final Users updatedBy =userAddressDTO.getUpdatedBy()!=null?new Users(userAddressDTO.getUpdatedBy()):null;
        userAddress.setUpdatedBy(updatedBy);
        final Users deletedBy = userAddressDTO.getDeletedBy()!=null?new Users(userAddressDTO.getDeletedBy()):null;
        userAddress.setDeletedBy(deletedBy);
        userAddress.setIsPrimary(userAddressDTO.getIsPrimary());
        return userAddress;
    }
}
