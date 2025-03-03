package com.shoppy.Shoppy.controller;

import com.shoppy.Shoppy.DTOs.LoginDTO;
import com.shoppy.Shoppy.DTOs.forCreate.UsersDTOForCreate;
import com.shoppy.Shoppy.DTOs.forDisplay.UsersDTOForDisplay;
import com.shoppy.Shoppy.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin
@RestController
@RequestMapping(value = "/api/users")
public class UsersController {

    @Autowired
    private UserService usersService;

    @GetMapping
    public ResponseEntity<List<UsersDTOForDisplay>> getAllUsers() {
        return ResponseEntity.ok(usersService.findAll());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UsersDTOForDisplay> getUsers(@PathVariable(name = "userId") final Integer userId) {
        UsersDTOForDisplay usersDTO=usersService.get(userId);
        if (usersDTO==null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(usersDTO);
    }

    @PostMapping
    public ResponseEntity<UsersDTOForDisplay> createUsers(@RequestBody @Valid final UsersDTOForCreate usersDTO) {
        final UsersDTOForDisplay usersDTO1 = usersService.create(usersDTO);
        if (usersDTO1==null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.status(HttpStatus.CREATED).body(usersDTO1);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UsersDTOForDisplay> updateUsers(@PathVariable(name = "userId") final Integer userId,
            @RequestBody @Valid final UsersDTOForCreate usersDTO) {
        UsersDTOForDisplay usersDTO1=usersService.update(userId, usersDTO);
        if (usersDTO1==null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(usersDTO1);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUsers(@PathVariable(name = "userId") final Integer userId) {
        usersService.delete(userId);
        return ResponseEntity.noContent().build();
    }

//    @PostMapping("/login")
//    public ResponseEntity<Boolean> login(@RequestBody LoginDTO loginDTO) {
//        Boolean login=usersService.login(loginDTO.getUsername(), loginDTO.getPassword());
//        return ResponseEntity.ok(login);
//    }
}
