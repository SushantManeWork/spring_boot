package com.shoppy.Shoppy.services;

import com.shoppy.Shoppy.DTOs.forCreate.UserPersonalDTOForCreate;
import com.shoppy.Shoppy.DTOs.forDisplay.UserPersonalDTOForDisplay;
import com.shoppy.Shoppy.entity.UserPersonal;
import com.shoppy.Shoppy.exception.ValidationException;
import com.shoppy.Shoppy.repository.UserPersonalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserPersonalService {

    @Autowired
    private UserPersonalRepository userPersonalRepository;

    public List<UserPersonalDTOForDisplay> findAll() {
        final List<UserPersonal> userPersonals = userPersonalRepository.findAll(Sort.by("userPersonalId"));
        return userPersonals.stream().filter(userPersonal -> userPersonal.getDeletedBy()==null)
                .map(UserPersonalDTOForDisplay::mapToDTO)
                .toList();
    }

    public UserPersonalDTOForDisplay get(final Integer userPersonalId) {
        List<String> exception=new ArrayList<>();
        if (userPersonalId<=0)
            exception.add("Invalid id");
        UserPersonalDTOForDisplay userPersonalDTO=userPersonalRepository.findById(userPersonalId).filter(userPersonal -> userPersonal.getDeletedBy()==null)
                .map(UserPersonalDTOForDisplay::mapToDTO)
                .orElse(null);
        if (userPersonalDTO==null)
            exception.add("User not found");
        if (!exception.isEmpty())
            throw new ValidationException(String.join(",\n",exception));
        return userPersonalDTO;
    }

    public UserPersonalDTOForDisplay create(final UserPersonalDTOForCreate userPersonalDTO) {
        List<String> exception=new ArrayList<>();
        if (userPersonalDTO.getUser()<=0 || userPersonalDTO.getFirstName().isBlank() || userPersonalDTO.getMiddleName().isBlank() ||
        userPersonalDTO.getLastName().isBlank() || userPersonalDTO.getEducation().isBlank())
            exception.add("Invalid Details");
        boolean userUserPersonal=userPersonalRepository.existsByUserUserId(userPersonalDTO.getUser());
        if (userUserPersonal)
            exception.add("User's details already exist");
        if (!exception.isEmpty())
            throw new ValidationException(String.join(",\n",exception));

        final UserPersonal userPersonal = UserPersonalDTOForCreate.mapToEntity(userPersonalDTO,new UserPersonal());
        userPersonal.setCreatedOn(LocalDate.now());
        return UserPersonalDTOForDisplay.mapToDTO(userPersonalRepository.save(userPersonal));
    }

    public UserPersonalDTOForDisplay update(final Integer userPersonalId, final UserPersonalDTOForCreate userPersonalDTO) {
        List<String> exception=new ArrayList<>();
        if (userPersonalId<=0 ||userPersonalDTO.getUser()<=0 || userPersonalDTO.getFirstName().isBlank() || userPersonalDTO.getMiddleName().isBlank() ||
                userPersonalDTO.getLastName().isBlank() || userPersonalDTO.getEducation().isBlank()||userPersonalDTO.getUpdatedBy()<=0)
            exception.add("Invalid Details");
        UserPersonal userUserPersonal=userPersonalRepository.findByUserUserId(userPersonalDTO.getUser());
        if (userUserPersonal!=null && userUserPersonal.getUser().getUserId()!=userPersonalDTO.getUser() )
            exception.add("User's details already exist");
         UserPersonal userPersonal = userPersonalRepository.findById(userPersonalId).filter(userPersonal1 -> userPersonal1.getDeletedBy()==null)
                .orElse(null);
        if (userPersonal==null)
            exception.add("Userpersonal not found for given id");
        if (!exception.isEmpty())
            throw new ValidationException(String.join(",\n",exception));

        UserPersonalDTOForCreate.mapToEntity(userPersonalDTO,userPersonal);
        userPersonal.setUpdatedOn(LocalDate.now());
        return UserPersonalDTOForDisplay.mapToDTO(userPersonalRepository.save(userPersonal));
    }

    public void delete(final Integer userPersonalId) {
        userPersonalRepository.deleteById(userPersonalId);
    }
}
