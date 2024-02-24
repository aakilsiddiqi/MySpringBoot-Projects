package com.login.service;

import com.login.dto.PersonalDetailsDTO;
import com.login.dto.UserDTO;
import com.login.exception.LoginApplicationException;

public interface UserService {
    public abstract PersonalDetailsDTO userLogin(UserDTO userDTO) throws LoginApplicationException;
    public abstract String addUser(UserDTO userDTO) throws LoginApplicationException;
}
