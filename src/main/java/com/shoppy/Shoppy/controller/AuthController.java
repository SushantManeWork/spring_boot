package com.shoppy.Shoppy.controller;

import com.shoppy.Shoppy.DTOs.LoginDTO;
import com.shoppy.Shoppy.DTOs.LoginResponseDTO;
import com.shoppy.Shoppy.DTOs.forDisplay.UsersDTOForDisplay;
import com.shoppy.Shoppy.entity.Users;
import com.shoppy.Shoppy.services.JwtService;
import com.shoppy.Shoppy.services.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@CrossOrigin
@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    @Autowired
    private UserService usersService;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginDTO loginDTO) {

        Users users=usersService.login(loginDTO.getUsername(), loginDTO.getPassword());
        if (users!=null){
            String token=jwtService.generateToken(UsersDTOForDisplay.mapToDTO(users));
            Object response=new LoginResponseDTO(users.getUserId(),token);
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.ok(null);
    }

}
