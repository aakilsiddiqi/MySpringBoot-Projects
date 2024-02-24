package com.login.controller;

import com.login.dto.PersonalDetailsDTO;
import com.login.dto.UserDTO;
import com.login.exception.LoginApplicationException;
import com.login.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/adduser")
    public ResponseEntity<String> addUser(@Valid @RequestBody UserDTO userDTO) throws LoginApplicationException{
        userService.addUser(userDTO);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }
    @PostMapping("/login")
    public ResponseEntity<PersonalDetailsDTO> login(@Valid @RequestBody UserDTO userDTO) throws LoginApplicationException {
        PersonalDetailsDTO dto = userService.userLogin(userDTO);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

}
