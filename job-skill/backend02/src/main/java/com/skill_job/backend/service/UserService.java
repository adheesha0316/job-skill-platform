package com.skill_job.backend.service;

import com.skill_job.backend.dto.LoginDto;
import com.skill_job.backend.dto.UserDto;
import com.skill_job.backend.dto.UserDtoReturn;

public interface UserService {
    public UserDtoReturn registerUser(UserDto userDto);
    public LoginDto loginUser(UserDto userDto);
}
