package com.example.demo.controller;


import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UserDTO;
import com.example.demo.model.User;
import com.example.demo.service.UserService;


import org.slf4j.Logger;
/**
 * @author Sara Montes
 * @version 1.0
 */

@RestController
@RequestMapping("api/users/v1")
public class UserController {
    private final Logger log = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;
	
	@Autowired
    private ModelMapper modelMapper;

    /**
     * Get a user by id 
     * @param id - user id to search.
     * @return User found by the id.
     */
    @GetMapping
    public UserDTO getUser(@RequestParam Long id) {
    	return convertToDTO(userService.findByUserId(id));
    }

    
    /**
     * Get all the users. 
     * @return List of all users.
     */
    @GetMapping("/users")
    public List<UserDTO> getUsers() {
    	return userService.findAll()
    			.stream()
    			.map(this::convertToDTO)
    			.collect(Collectors.toList());
    }

    /**
     * Creates a user
     * @param user to be created
     *            - User JSON in Request Body
     * @return UserDTO created.
     */
    @PostMapping
    public UserDTO createUser(@RequestBody UserDTO userDTO) {
    	User user = convertToEntity(userDTO);
        User userCreated = userService.createUser(user);
        return convertToDTO(userCreated);
    	
    }

    /**
     * Update a user of which client sends as JSON in request body.
     * 
     * @param user
     * @return user updated.
     */
    @PutMapping
    public UserDTO updateUser(@RequestBody UserDTO userDTO) {
    	log.info("Request to update user: {}", userDTO);
    	User user = convertToEntity(userDTO);
    	return convertToDTO(userService.updateUser(user));
    }

    /**
     * Delete a user
     * 
     * @param id
     *            - Path variable
     */
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
    	log.info("Request to delete user: {}", id);
    	userService.deleteUser(id);
    }

    private UserDTO convertToDTO(User user) {
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        return userDTO;
    }
    
    private User convertToEntity(UserDTO userDTO){
        User user = modelMapper.map(userDTO, User.class);
        return user;
    }
}
