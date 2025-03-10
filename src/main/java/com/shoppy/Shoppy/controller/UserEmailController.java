package com.shoppy.Shoppy.controller;

import com.shoppy.Shoppy.DTOs.forRequest.UserEmailDTOForCreate;
import com.shoppy.Shoppy.DTOs.forResponse.UserEmailDTOForDisplay;
import com.shoppy.Shoppy.services.UserEmailService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/api/userEmails")
public class UserEmailController {

    @Autowired
    private   UserEmailService _userEmailService;

    @GetMapping
    public ResponseEntity<List<UserEmailDTOForDisplay>> getAllUserEmails() {
        return ResponseEntity.ok(_userEmailService.findAll());
    }

    @GetMapping("/{userEmailId}")
    public ResponseEntity<UserEmailDTOForDisplay> getUserEmail(
            @PathVariable(name = "userEmailId")   Integer userEmailId) {
        UserEmailDTOForDisplay emailDTO=_userEmailService.get(userEmailId);

        if (emailDTO==null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(emailDTO);
    }

    @PostMapping
    public ResponseEntity<UserEmailDTOForDisplay> createUserEmail(
            @RequestBody @Valid UserEmailDTOForCreate userEmailDTO) {
        UserEmailDTOForDisplay emailDTO = _userEmailService.create(userEmailDTO);

        if (emailDTO==null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.status(HttpStatus.CREATED).body(emailDTO);
    }

    @PutMapping("/{userEmailId}")
    public ResponseEntity<UserEmailDTOForDisplay> updateUserEmail(
            @PathVariable(name = "userEmailId")   Integer userEmailId,
            @RequestBody @Valid   UserEmailDTOForCreate userEmailDTO) {
        UserEmailDTOForDisplay emailDTO=_userEmailService.update(userEmailId, userEmailDTO);

        if (emailDTO==null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(emailDTO);
    }

    @DeleteMapping("/{userEmailId}")
    public ResponseEntity<Void> deleteUserEmail(
            @PathVariable(name = "userEmailId")   Integer userEmailId) {
        _userEmailService.delete(userEmailId);
        return ResponseEntity.noContent().build();
    }

}
