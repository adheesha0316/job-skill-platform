package com.skill_job.backend.service.Impl;

import com.skill_job.backend.dto.LoginDto;
import com.skill_job.backend.dto.UserDto;
import com.skill_job.backend.dto.UserDtoReturn;
import com.skill_job.backend.entity.User;
import com.skill_job.backend.repo.UserRepo;
import com.skill_job.backend.service.UserService;
import com.skill_job.backend.util.JWTTokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final JWTTokenGenerator jwtTokenGenerator;

    @Autowired
    public UserServiceImpl(UserRepo userRepo, JWTTokenGenerator jwtTokenGenerator) {
        this.userRepo = userRepo;
        this.jwtTokenGenerator = jwtTokenGenerator;
    }


    @Override
    public UserDtoReturn registerUser(UserDto userDto) {
        String encodedPassword = Base64.getEncoder().encodeToString(userDto.getPassword().getBytes());

        User save = userRepo.save(new User(null, userDto.getUser_name(), userDto.getEmail(), encodedPassword, userDto.getRole()));
        if(save != null) {
            return new UserDtoReturn(save.getEmail(), "Registered Successfully");
        }
        return new UserDtoReturn(save.getEmail(), "Registered failed");
    }

    @Override
    public LoginDto loginUser(UserDto userDto) {
        Optional<User> userByEmail = userRepo.getUsersByEmail(userDto.getEmail());
        if(userByEmail.isPresent()) {
            byte[] decodedBytes = Base64.getDecoder().decode(userByEmail.get().getPassword());
            String decodedPassword = new String(decodedBytes);

            if (decodedPassword.equals(userDto.getPassword())) {
                String token = jwtTokenGenerator.generateToken(userByEmail.get());
                return new LoginDto(userByEmail.get().getEmail(), token);
            }
        }

        return new LoginDto("no email found", null);
    }
}
