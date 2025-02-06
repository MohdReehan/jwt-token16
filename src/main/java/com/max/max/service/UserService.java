package com.max.max.service;



import com.max.max.model.User;
import com.max.max.repsitories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public User addUser(User user) {
       return userRepo.save(user);
    }
    public User getUser(Long id) {
        Optional<User> byId = userRepo.findById(id);
        boolean present = byId.isPresent();
        if (present){
            return byId.get();
        }
        return null;
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }


    public User userByName(String name) {
        Optional<User> byName = userRepo.findByName(name);
        if (byName.isPresent()){
            return byName.get();
        }
return null;
    }
    public User updateUser(Long id,User user) {
        Optional<User> byId = userRepo.findById(id);
        boolean present = byId.isPresent();
        if (present){
            User user1 = byId.get();
            user1.setName(user.getName());
            user1.setAddress(user.getAddress());
            user1.setPhone(user.getPhone());
            return userRepo.save(user1);
        }
      return null;
    }

    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }
}
