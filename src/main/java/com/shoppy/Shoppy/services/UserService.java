package com.shoppy.Shoppy.services;

import com.shoppy.Shoppy.DTOs.forCreate.UsersDTOForCreate;
import com.shoppy.Shoppy.DTOs.forDisplay.UsersDTOForDisplay;
import com.shoppy.Shoppy.entity.Users;
import com.shoppy.Shoppy.exception.ValidationException;
import com.shoppy.Shoppy.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    private final BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();

    public List<UsersDTOForDisplay> findAll() {
        final List<Users> users = usersRepository.findAll();
        return users.stream().filter(user->user.getDeletedBy()==null)
                .map(UsersDTOForDisplay::mapToDTO)
                .toList();
    }

     public UsersDTOForDisplay get(final Integer userId) {
        List<String> exception=new ArrayList<>();
        if (userId<=0)
            exception.add("Id is wrong");
        UsersDTOForDisplay users=usersRepository.findById(userId).filter(user->user.getDeletedBy()==null)
                .map(UsersDTOForDisplay::mapToDTO)
                .orElse(null);
        if (users==null)
            exception.add("User is not found");

        if (!exception.isEmpty())
            throw new ValidationException(String.join(",\n", exception));
        return users;
    }

    public UsersDTOForDisplay create(final UsersDTOForCreate usersDTO) {
        List<String> exception=new ArrayList<>();
        if (usersDTO.getUsername().isBlank() || usersDTO.getPassword().isBlank() || usersDTO.getCreatedBy().isBlank())
            exception.add("Enter correct details");
        Users usernameUser=usersRepository.findByUsername(usersDTO.getUsername());
        if (usernameUser!=null)
            exception.add("Username is already exist");
        if (!exception.isEmpty())
            throw new ValidationException(String.join(",\n", exception));

        final Users users =UsersDTOForCreate.mapToEntity(usersDTO,new Users());
        users.setCreatedOn(LocalDate.now());
        users.setPassword(encoder.encode(usersDTO.getPassword()));
        return UsersDTOForDisplay.mapToDTO(usersRepository.save(users));
    }

    public UsersDTOForDisplay update(final Integer userId, final UsersDTOForCreate usersDTO) {
        List<String> exception=new ArrayList<>();
        if (userId<=0 ||usersDTO.getUsername().isBlank() || usersDTO.getPassword().isBlank() || usersDTO.getCreatedBy().isBlank()|| usersDTO.getUpdatedBy().isBlank())
            exception.add("Enter correct details");
        Users usernameUser=usersRepository.findByUsername(usersDTO.getUsername());
        if (usernameUser!=null && usernameUser.getUserId()!=userId)
            exception.add("Username is already exist");
         Users users = usersRepository.findById(userId).filter(user->user.getDeletedBy()==null)
                .orElse(null);
        if (users==null)
            exception.add("User not found");
        if (!exception.isEmpty())
            throw new ValidationException(String.join(",\n", exception));

        UsersDTOForCreate.mapToEntity(usersDTO,users);
        users.setUpdatedOn(LocalDate.now());
        if (!isPassword(usersDTO.getPassword(),users.getPassword())){
        users.setPassword(encoder.encode(usersDTO.getPassword()));
        }
        return UsersDTOForDisplay.mapToDTO(usersRepository.save(users));
    }

    public void delete(final Integer userId) {
        usersRepository.deleteById(userId);
    }

    private boolean isPassword(String password, String hashedPassword) {
        return encoder.matches(password,hashedPassword);
    }

    public Users login(String username,String password){
//        Users user=usersRepository.findByUsername(username);
//        if (isPassword(password,user.getPassword()))
//            return true;

        Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
        if (authentication.isAuthenticated()){
            Users users=usersRepository.findByUsername(username);
            return users;
        }
        return null;
    }
}
