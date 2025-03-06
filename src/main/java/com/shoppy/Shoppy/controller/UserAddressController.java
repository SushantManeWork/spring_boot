package com.shoppy.Shoppy.controller;

import com.shoppy.Shoppy.DTOs.forCreate.UserAddressDTOForCreate;
import com.shoppy.Shoppy.DTOs.forDisplay.UserAddressDTOForDisplay;
import com.shoppy.Shoppy.services.UserAddressService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/userAddresses")
public class UserAddressController {

    @Autowired
    private UserAddressService _userAddressService;

    @GetMapping
    public ResponseEntity<List<UserAddressDTOForDisplay>> getAllUserAddresses() {
        return ResponseEntity.ok(_userAddressService.findAll());
    }

    @GetMapping("/{userAddressId}")
    public ResponseEntity<UserAddressDTOForDisplay> getUserAddress(
            @PathVariable(name = "userAddressId") final Integer userAddressId) {
        UserAddressDTOForDisplay addressDTO=_userAddressService.get(userAddressId);

        if (addressDTO==null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(addressDTO);
    }

    @PostMapping
    public ResponseEntity<UserAddressDTOForDisplay> createUserAddress(
            @RequestBody @Valid final UserAddressDTOForCreate userAddressDTO) {
        final UserAddressDTOForDisplay addressDTO = _userAddressService.create(userAddressDTO);

        if (addressDTO==null)
            return ResponseEntity.badRequest().build();
        return  ResponseEntity.status(HttpStatus.CREATED).body(addressDTO);
    }

    @PutMapping("/{userAddressId}")
    public ResponseEntity<UserAddressDTOForDisplay> updateUserAddress(
            @PathVariable(name = "userAddressId") final Integer userAddressId,
            @RequestBody @Valid final UserAddressDTOForCreate userAddressDTO) {
        UserAddressDTOForDisplay addressDTO=_userAddressService.update(userAddressId, userAddressDTO);

        if (addressDTO==null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(addressDTO);
    }

    @DeleteMapping("/{userAddressId}")
    public ResponseEntity<Void> deleteUserAddress(
            @PathVariable(name = "userAddressId") final Integer userAddressId) {
        _userAddressService.delete(userAddressId);
        return ResponseEntity.noContent().build();
    }

}
