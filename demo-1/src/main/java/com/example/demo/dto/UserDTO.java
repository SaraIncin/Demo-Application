package com.example.demo.dto;

import lombok.Data;

@Data
public class UserDTO {
	private long userId;
	private String username;
	private String password;
	private String firstName;
	private String middleName;
	private String lastName;
    private String email;

}
