package com.shoppy.Shoppy.controller;

import com.shoppy.Shoppy.DTOs.LoginDTO;
import com.shoppy.Shoppy.DTOs.LoginResponseDTO;
import com.shoppy.Shoppy.DTOs.forResponse.UsersDTOForDisplay;
import com.shoppy.Shoppy.entity.Users;
import com.shoppy.Shoppy.services.JwtService;
import com.shoppy.Shoppy.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    @Autowired
    private UserService _usersService;

    @Autowired
    private JwtService _jwtService;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginDTO loginDTO) {
        Users users=_usersService.login(loginDTO.getUsername(), loginDTO.getPassword());

        if (users!=null){
            String token=_jwtService.generateToken(UsersDTOForDisplay.mapToDTO(users));
            Object response=new LoginResponseDTO(users.getUserId(),token);
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.ok(null);
    }

}
