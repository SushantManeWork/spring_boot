package com.shoppy.Shoppy.controller;

import com.shoppy.Shoppy.DTOs.forCreate.UserPersonalDTOForCreate;
import com.shoppy.Shoppy.DTOs.forDisplay.UserPersonalDTOForDisplay;
import com.shoppy.Shoppy.services.UserPersonalService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/api/userPersonals")
public class UserPersonalController {

    @Autowired
    private UserPersonalService userPersonalService;

    @GetMapping
    public ResponseEntity<List<UserPersonalDTOForDisplay>> getAllUserPersonals() {
        return ResponseEntity.ok(userPersonalService.findAll());
    }

    @GetMapping("/{userPersonalId}")
    public ResponseEntity<UserPersonalDTOForDisplay> getUserPersonal(
            @PathVariable(name = "userPersonalId") final Integer userPersonalId) {
        UserPersonalDTOForDisplay personalDTO=userPersonalService.get(userPersonalId);
        if (personalDTO==null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(personalDTO);
    }

    @PostMapping
    public ResponseEntity<UserPersonalDTOForDisplay> createUserPersonal(
            @RequestBody @Valid final UserPersonalDTOForCreate userPersonalDTO) {
        final UserPersonalDTOForDisplay personalDTO = userPersonalService.create(userPersonalDTO);
        if (personalDTO==null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.status(HttpStatus.CREATED).body(personalDTO);
    }

    @PutMapping("/{userPersonalId}")
    public ResponseEntity<UserPersonalDTOForDisplay> updateUserPersonal(
            @PathVariable(name = "userPersonalId") final Integer userPersonalId,
            @RequestBody @Valid final UserPersonalDTOForCreate userPersonalDTO) {
        UserPersonalDTOForDisplay personalDTO=userPersonalService.update(userPersonalId, userPersonalDTO);
        if (personalDTO==null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(personalDTO);
    }

    @DeleteMapping("/{userPersonalId}")
    public ResponseEntity<Void> deleteUserPersonal(
            @PathVariable(name = "userPersonalId") final Integer userPersonalId) {
        userPersonalService.delete(userPersonalId);
        return ResponseEntity.noContent().build();
    }

}
