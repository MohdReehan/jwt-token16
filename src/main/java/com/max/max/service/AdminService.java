package com.max.max.service;


import com.max.max.model.Admin;
import com.max.max.repsitories.AdminRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    private AdminRepo adminRepo;

    public Admin getAdmin(String username) {
      return   adminRepo.findByName(username);

    }
}
