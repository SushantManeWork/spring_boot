package com.shoppy.Shoppy.services;

import com.shoppy.Shoppy.DTOs.forRequest.UserAddressDTOForCreate;
import com.shoppy.Shoppy.DTOs.forResponse.UserAddressDTOForDisplay;
import com.shoppy.Shoppy.entity.UserAddress;
import com.shoppy.Shoppy.exception.ValidationException;
import com.shoppy.Shoppy.repository.UserAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserAddressService {

    @Autowired
    private UserAddressRepository _userAddressRepository;

    public List<UserAddressDTOForDisplay> findAll() {
        final List<UserAddress> userAddresses = _userAddressRepository.findAll(Sort.by("userAddressId"));
        return userAddresses.stream().filter(userAddress -> userAddress.getDeletedBy()==null)
                .map(UserAddressDTOForDisplay::mapToDTO)
                .toList();
    }

    public UserAddressDTOForDisplay get(final Integer userAddressId) {
        List<String> exception=new ArrayList<>();

        if (userAddressId<=0)
            exception.add("Id is wrong");
        UserAddressDTOForDisplay userAddressDTO=_userAddressRepository.findById(userAddressId).filter(userAddress -> userAddress.getDeletedBy()==null)
                .map(UserAddressDTOForDisplay::mapToDTO)
                .orElse(null);

        if (userAddressDTO==null)
            exception.add("Address not found");
        if (!exception.isEmpty())
            throw new ValidationException(String.join(",\n", exception));
        return userAddressDTO;
    }

    public UserAddressDTOForDisplay create(final UserAddressDTOForCreate userAddressDTO) {
        List<String> exception=new ArrayList<>();

        if (userAddressDTO.getUserId()<=0 || userAddressDTO.getCity().isBlank()||userAddressDTO.getState().isBlank()||userAddressDTO.getCreatedBy()<=0)
            exception.add("Invalid details");
        UserAddress userUserAddress=_userAddressRepository.findByUserUserId(userAddressDTO.getUserId());

        if (userUserAddress!=null)
            exception.add("User's address is already exist");
        if (!exception.isEmpty())
            throw new ValidationException(String.join(",\n",exception));
        final UserAddress userAddress = UserAddressDTOForCreate.mapToEntity(userAddressDTO,new UserAddress());
        userAddress.setCreatedOn(LocalDate.now());
        UserAddressDTOForDisplay addressDTO=UserAddressDTOForDisplay.mapToDTO(_userAddressRepository.save(userAddress));
        return addressDTO;
    }

    public UserAddressDTOForDisplay update(final Integer userAddressId, final UserAddressDTOForCreate userAddressDTO) {
        List<String> exception=new ArrayList<>();

        if (userAddressId<=0 ||userAddressDTO.getUserId()<=0 || userAddressDTO.getCity().isBlank()||userAddressDTO.getState().isBlank()
                ||userAddressDTO.getCreatedBy()<=0 || userAddressDTO.getUpdatedBy()<=0)
            exception.add("Invalid details");
        UserAddress userAddress = _userAddressRepository.findById(userAddressId).filter(userAddress1 -> userAddress1.getDeletedBy()==null)
                .orElse(null);

        if (userAddress==null)
            exception.add("Address is not exist for provided id");
        if (!exception.isEmpty())
            throw new ValidationException(String.join(",\n",exception));
        UserAddressDTOForCreate.mapToEntity(userAddressDTO,userAddress);
        userAddress.setUpdatedOn(LocalDate.now());
        return UserAddressDTOForDisplay.mapToDTO(_userAddressRepository.save(userAddress));
    }

    public void delete(final Integer userAddressId) {
        _userAddressRepository.deleteById(userAddressId);
    }
}
