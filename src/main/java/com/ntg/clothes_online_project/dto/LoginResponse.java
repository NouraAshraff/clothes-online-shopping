package com.ntg.clothes_online_project.dto;

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
