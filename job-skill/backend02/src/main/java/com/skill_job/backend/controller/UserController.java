package com.skill_job.backend.controller;

import com.skill_job.backend.dto.LoginDto;
import com.skill_job.backend.dto.UserDto;
import com.skill_job.backend.dto.UserDtoReturn;
import com.skill_job.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDtoReturn> userRegister(@RequestBody UserDto userDto) {
        UserDtoReturn userDtoReturn = userService.registerUser(userDto);
        return new ResponseEntity<>(userDtoReturn, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginDto> userLogin(@RequestBody UserDto userDto) {
        LoginDto loginDto = userService.loginUser(userDto);
        return new ResponseEntity<>(loginDto, HttpStatus.OK);
    }


}
