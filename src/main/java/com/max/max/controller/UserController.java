package com.max.max.controller;



import com.max.max.config.TokenManager;
import com.max.max.model.User;
import com.max.max.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenManager tokenManager;

@PostMapping("/addUser")
    public User addUser(@RequestBody User user) {

    User user1 = userService.addUser(user);

    return user1;
    }
    @GetMapping("/getUser/{id}")
    public User addUser(@PathVariable Long id) {

        User user1 = userService.getUser(id);

        return user1;
    }
    @PutMapping("/updateUser/{id}")
  public User updateUser(@PathVariable Long id,  @RequestBody User user) {
        User updated = userService.updateUser(id, user);
        return updated;
    }

    @PostMapping("/genearateT")
    public String hello(@RequestBody UserDetails userDetails) {
        String jwtToken = tokenManager.generateJwtToken(userDetails);

        return "hello"+jwtToken;
    }
    @DeleteMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable Long id) {
    userService.deleteUser(id);
    return "User deleted";

    }


    @GetMapping("/getAllUser")
    public List<User> getUsers() {
        List<User> allUsers = userService.getAllUsers();
        return allUsers;

    }
}
