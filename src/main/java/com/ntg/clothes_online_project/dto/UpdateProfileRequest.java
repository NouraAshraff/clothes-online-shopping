package com.ntg.clothes_online_project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateProfileRequest {
    private String name;
    private String email;
    private String password;
}
