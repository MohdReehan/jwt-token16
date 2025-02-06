package com.max.max.repsitories;

import com.max.max.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AdminRepo extends JpaRepository<Admin, Long> {
    
    Admin findByName(String name);
}
