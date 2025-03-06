package com.shoppy.Shoppy.services;

import com.shoppy.Shoppy.DTOs.forCreate.UserEmailDTOForCreate;
import com.shoppy.Shoppy.DTOs.forDisplay.UserEmailDTOForDisplay;
import com.shoppy.Shoppy.entity.UserEmail;
import com.shoppy.Shoppy.exception.ValidationException;
import com.shoppy.Shoppy.repository.UserEmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserEmailService {

    @Autowired
    private UserEmailRepository _userEmailRepository;

    public List<UserEmailDTOForDisplay> findAll() {
        final List<UserEmail> userEmails = _userEmailRepository.findAll(Sort.by("userEmailId"));
        return userEmails.stream().filter(userEmail -> userEmail.getDeletedBy()==null)
                .map(UserEmailDTOForDisplay::mapToDTO)
                .toList();
    }

    public UserEmailDTOForDisplay get(final Integer userEmailId) {
        List<String> exception=new ArrayList<>();

        if (userEmailId<=0)
            exception.add("Id is wrong");
        UserEmailDTOForDisplay userEmailDTO=_userEmailRepository.findById(userEmailId).filter(userEmail -> userEmail.getDeletedBy()==null)
                .map(UserEmailDTOForDisplay::mapToDTO)
                .orElse(null);

        if (userEmailDTO==null)
            exception.add("Email not found");
        if (!exception.isEmpty())
            throw new ValidationException(String.join(",\n", exception));
        return userEmailDTO;
    }

    public UserEmailDTOForDisplay create(final UserEmailDTOForCreate userEmailDTO) {
        List<String> exception=new ArrayList<>();

        if (!userEmailDTO.getEmail().contains("@") || userEmailDTO.getUser()<=0 || userEmailDTO.getCreatedBy()<=0)
            exception.add("Invalid details");
        UserEmail emailUserEmail=_userEmailRepository.findByEmail(userEmailDTO.getEmail());
        UserEmail userAndIsPrimaryUserEmail=_userEmailRepository.findByUserIdAndIsPrimary(userEmailDTO.getUser());

        if (emailUserEmail!=null)
            exception.add("Email already exists");
        if (userAndIsPrimaryUserEmail!=null && userEmailDTO.getIsPrimary())
            exception.add("Primary email already exist");
        if (!exception.isEmpty())
            throw new ValidationException(String.join(",\n", exception));
         UserEmail userEmail = UserEmailDTOForCreate.mapToEntity(userEmailDTO,new UserEmail());
         userEmail.setCreatedOn(LocalDate.now());
        return  UserEmailDTOForDisplay.mapToDTO(_userEmailRepository.save(userEmail));
    }

    public UserEmailDTOForDisplay update(final Integer userEmailId, final UserEmailDTOForCreate userEmailDTO) {
        List<String> exception=new ArrayList<>();

        if (userEmailId<=0 ||!userEmailDTO.getEmail().contains("@") || userEmailDTO.getUser()<=0 || userEmailDTO.getUpdatedBy()<=0)
            exception.add("Invalid details");
         UserEmail userEmail = _userEmailRepository.findById(userEmailId).filter(userEmail1 -> userEmail1.getDeletedBy()==null)
                .orElse(null);
        UserEmail emailUserEmail=_userEmailRepository.findByEmail(userEmailDTO.getEmail());
        UserEmail userAndIsPrimaryUserEmail=_userEmailRepository.findByUserIdAndIsPrimary(userEmailDTO.getUser());

        if (emailUserEmail!=null && emailUserEmail.getUserEmailId()!=userEmailId)
            exception.add("Primary email already exist");
        if (userAndIsPrimaryUserEmail!=null && userEmailDTO.getIsPrimary() && userAndIsPrimaryUserEmail.getUserEmailId()!=userEmailId)
            exception.add("Primary email is already exist");
        if (userEmail==null)
            exception.add("Email is not Found for provided id");
        if (!exception.isEmpty())
            throw new ValidationException(String.join(",\n", exception));
        UserEmailDTOForCreate.mapToEntity(userEmailDTO,userEmail);
        userEmail.setUserEmailId(userEmailId);
        userEmail.setUpdatedOn(LocalDate.now());
        return UserEmailDTOForDisplay.mapToDTO(_userEmailRepository.save(userEmail));
    }

    public void delete(final Integer userEmailId) {
        _userEmailRepository.deleteById(userEmailId);
    }
}
