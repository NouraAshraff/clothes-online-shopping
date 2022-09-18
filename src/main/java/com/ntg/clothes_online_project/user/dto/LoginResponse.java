package com.ntg.clothes_online_project.user.dto;

import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoginResponse {

    private String name;
    private String email;
    private String token;


}
