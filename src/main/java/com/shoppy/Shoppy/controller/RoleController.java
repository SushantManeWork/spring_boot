package com.shoppy.Shoppy.controller;

import com.shoppy.Shoppy.entity.Role;
import com.shoppy.Shoppy.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/roles")
public class RoleController {

    @Autowired
    private RoleService _roleService;

    @GetMapping
    public ResponseEntity<List<Role>> getAllUsers() {
        return ResponseEntity.ok(_roleService.findAll());
    }
}
