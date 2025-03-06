package com.shoppy.Shoppy.services;

import com.shoppy.Shoppy.entity.Role;
import com.shoppy.Shoppy.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleRepository _roleRepository;

    public List<Role> findAll(){
        return _roleRepository.findAll();
    }

}
