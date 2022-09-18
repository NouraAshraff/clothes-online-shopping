package com.ntg.clothes_online_project.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
	private String name;
	private String userName;
	private String email;
	private String gender;
	private String phoneNum;
	private String city;
	private String address;

}
