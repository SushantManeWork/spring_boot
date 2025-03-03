package com.shoppy.Shoppy.services;

import com.shoppy.Shoppy.DTOs.forCreate.UserPhoneDTOForCreate;
import com.shoppy.Shoppy.DTOs.forDisplay.UserPhoneDTOForDisplay;
import com.shoppy.Shoppy.entity.UserPhone;
import com.shoppy.Shoppy.exception.ValidationException;
import com.shoppy.Shoppy.repository.UserPhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserPhoneService {

    @Autowired
    private UserPhoneRepository userPhoneRepository;

    public List<UserPhoneDTOForDisplay> findAll() {
        final List<UserPhone> userPhones = userPhoneRepository.findAll(Sort.by("userPhoneId"));
        return userPhones.stream().filter(userPhone -> userPhone.getDeletedBy()==null)
                .map(UserPhoneDTOForDisplay::mapToDTO)
                .toList();
    }

    public UserPhoneDTOForDisplay get(final Integer userPhoneId) {
        List<String> exception=new ArrayList<>();
        if (userPhoneId<=0)
            exception.add("Id is wrong");
        UserPhoneDTOForDisplay userPhone=userPhoneRepository.findById(userPhoneId).filter(userPhone1 -> userPhone1.getDeletedBy()==null)
                .map(UserPhoneDTOForDisplay::mapToDTO)
                .orElse(null);
        if (userPhone==null)
            exception.add("UserPhone not found");
        if (!exception.isEmpty())
            throw new ValidationException(String.join(",\n", exception));
        return userPhone;
    }

    public UserPhoneDTOForDisplay create(final UserPhoneDTOForCreate userPhoneDTO) {
        List<String> exception=new ArrayList<>();
        if (userPhoneDTO.getPhone().length()!=10 || userPhoneDTO.getUser()<=0 || userPhoneDTO.getCreatedBy()<=0)
            exception.add("Enter correct details");
        UserPhone phoneUserPhone=userPhoneRepository.findByPhone(userPhoneDTO.getPhone());
        UserPhone userAndIsPrimaryUserPhone=userPhoneRepository.findByUserIdAndIsPrimary(userPhoneDTO.getUser());
        if (phoneUserPhone!=null)
            exception.add("UserPhone already exist");
        if (userAndIsPrimaryUserPhone!=null && userPhoneDTO.getIsPrimary())
            exception.add("Primary phone already exists");

        if (!exception.isEmpty())
            throw new ValidationException(String.join(",\n",exception));

        final UserPhone userPhone =UserPhoneDTOForCreate.mapToEntity(userPhoneDTO,new UserPhone());
        userPhone.setCreatedOn(LocalDate.now());
        return UserPhoneDTOForDisplay.mapToDTO(userPhoneRepository.save(userPhone));
    }

    public UserPhoneDTOForDisplay update(final Integer userPhoneId, final UserPhoneDTOForCreate userPhoneDTO) {
        List<String> exception=new ArrayList<>();
        if (userPhoneId<=0 ||userPhoneDTO.getPhone().length()!=10 || userPhoneDTO.getUser()<=0 ||userPhoneDTO.getUpdatedBy()<=0|| userPhoneDTO.getCreatedBy()<=0 )
            exception.add("Enter correct details");
         UserPhone userPhone = userPhoneRepository.findById(userPhoneId).filter(userPhone1 -> userPhone1.getDeletedBy()==null)
                .orElse(null);
        UserPhone phoneUserPhone=userPhoneRepository.findByPhone(userPhoneDTO.getPhone());
        UserPhone userAndIsPrimaryUserPhone=userPhoneRepository.findByUserIdAndIsPrimary(userPhoneDTO.getUser());
        if ((phoneUserPhone!=null && phoneUserPhone.getUserPhoneId()!=userPhoneId))
            exception.add("UserPhone already exist");

        if (userAndIsPrimaryUserPhone!=null && userPhoneDTO.getIsPrimary() && userAndIsPrimaryUserPhone.getUserPhoneId()!=userPhoneId)
            exception.add("Primary phone already exists");
        if (userPhone!=null)
            exception.add("Phone is not found");
        UserPhoneDTOForCreate.mapToEntity(userPhoneDTO,userPhone);
        userPhone.setUpdatedOn(LocalDate.now());
        return UserPhoneDTOForDisplay.mapToDTO(userPhoneRepository.save(userPhone));
    }

    public void delete(final Integer userPhoneId) {
        userPhoneRepository.deleteById(userPhoneId);
    }
}
