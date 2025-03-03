package com.shoppy.Shoppy.controller;

import com.shoppy.Shoppy.DTOs.forCreate.UserPhoneDTOForCreate;
import com.shoppy.Shoppy.DTOs.forDisplay.UserPhoneDTOForDisplay;
import com.shoppy.Shoppy.services.UserPhoneService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/api/userPhones")
public class UserPhoneController {

    @Autowired
    private UserPhoneService userPhoneService;

    @GetMapping
    public ResponseEntity<List<UserPhoneDTOForDisplay>> getAllUserPhones() {
        return ResponseEntity.ok(userPhoneService.findAll());
    }

    @GetMapping("/{userPhoneId}")
    public ResponseEntity<UserPhoneDTOForDisplay> getUserPhone(
            @PathVariable(name = "userPhoneId") final Integer userPhoneId) {
        UserPhoneDTOForDisplay phoneDTO=userPhoneService.get(userPhoneId);
        if (phoneDTO==null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(phoneDTO);
    }

    @PostMapping
    public ResponseEntity<UserPhoneDTOForDisplay> createUserPhone(
            @RequestBody @Valid final UserPhoneDTOForCreate userPhoneDTO) {
        final UserPhoneDTOForDisplay phoneDTO = userPhoneService.create(userPhoneDTO);
        if (phoneDTO==null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.status(HttpStatus.CREATED).body(phoneDTO);
    }

    @PutMapping("/{userPhoneId}")
    public ResponseEntity<UserPhoneDTOForDisplay> updateUserPhone(
            @PathVariable(name = "userPhoneId") final Integer userPhoneId,
            @RequestBody @Valid final UserPhoneDTOForCreate userPhoneDTO) {
        UserPhoneDTOForDisplay phoneDTO=userPhoneService.update(userPhoneId, userPhoneDTO);
        if (phoneDTO==null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(phoneDTO);
    }

    @DeleteMapping("/{userPhoneId}")
    public ResponseEntity<Void> deleteUserPhone(
            @PathVariable(name = "userPhoneId") final Integer userPhoneId) {
        userPhoneService.delete(userPhoneId);
        return ResponseEntity.noContent().build();
    }

}
