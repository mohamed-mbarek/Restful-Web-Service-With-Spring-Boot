package com.in28minutes.rest.webservices.restfulwebservices.controller;

import com.in28minutes.rest.webservices.restfulwebservices.entities.User;
import com.in28minutes.rest.webservices.restfulwebservices.exception.UserNotFoundException;
import com.in28minutes.rest.webservices.restfulwebservices.searvices.UserDaoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;

@RestController
public class HelloWordController {

    @Autowired
    UserDaoServices userDaoServices;

    @GetMapping(path = "/users")
    public List<User> getAllUsers(){
        return userDaoServices.findAll();
    }
    @GetMapping("/users/{id}")
    public User retrieveUser(@PathVariable int id){
        User  user= userDaoServices.findOne(id);
        if(user==null)
            throw  new UserNotFoundException("id:"+id);
        return user;
    }

    @PostMapping("/users")
    public ResponseEntity<User> retrieveUser(@RequestBody User user){
        User savedUser = userDaoServices.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }


}
