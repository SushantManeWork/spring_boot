package com.shoppy.Shoppy.DTOs.forDisplay;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.shoppy.Shoppy.entity.UserAddress;
import com.shoppy.Shoppy.entity.Users;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserAddressDTOForDisplay {

    private Integer userAddressId;

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
    private Integer user;

    @NotNull
    private Integer createdBy;

    private Integer updatedBy;


    public static UserAddressDTOForDisplay mapToDTO(final UserAddress userAddress) {
        UserAddressDTOForDisplay userAddressDTO=new UserAddressDTOForDisplay();
        userAddressDTO.setUserAddressId(userAddress.getUserAddressId());
        userAddressDTO.setCity(userAddress.getCity());
        userAddressDTO.setState(userAddress.getState());
        userAddressDTO.setZipcode(userAddress.getZipcode());
        userAddressDTO.setUser(userAddress.getUser() == null ? null : userAddress.getUser().getUserId());
        userAddressDTO.setCreatedBy(userAddress.getCreatedBy() == null ? null : userAddress.getCreatedBy().getUserId());
        userAddressDTO.setUpdatedBy(userAddress.getUpdatedBy() == null ? null : userAddress.getUpdatedBy().getUserId());
        userAddressDTO.setIsPrimary(userAddress.getIsPrimary());
        return userAddressDTO;
    }
}
