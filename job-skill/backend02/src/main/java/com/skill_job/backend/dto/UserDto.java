package com.skill_job.backend.dto;

import com.skill_job.backend.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Integer user_id;
    private String user_name;
    private String email;
    private String password;
    private Role role;
}
