package com.ntg.clothes_online_project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequest {

	private String name;

	private String userName;

	private String email;

	private String password;


	private String gender;


	private String phoneNumber;

	private String city;

	private String address;

	private Boolean isActive;


}
