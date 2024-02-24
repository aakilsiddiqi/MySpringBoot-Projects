package com.login.service;

import com.login.dto.PersonalDetailsDTO;
import com.login.dto.UserDTO;
import com.login.enitity.UserEntity;
import com.login.exception.LoginApplicationException;
import com.login.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@PropertySource("classpath:ValidationMessage.properties")
public class UserServiceImpl implements UserService{
    private UserEntity entity = new UserEntity();
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private Environment environment;
    @Override
    public PersonalDetailsDTO userLogin(UserDTO userDTO) throws LoginApplicationException {
        Optional<UserEntity> user = userRepository.findById(userDTO.getUserId());
        if (user.isEmpty()){
            throw new LoginApplicationException("check your user name");
        }else {
            if ((passwordEncoder.matches(userDTO.getUserPassword(),user.get().getUserPassword()))){
                PersonalDetailsDTO personalDetailsDTO = new PersonalDetailsDTO();
                personalDetailsDTO.setTitle(user.get().getTitle());
                personalDetailsDTO.setFirstName(user.get().getFirstName());
                personalDetailsDTO.setMiddleName(user.get().getMiddleName());
                personalDetailsDTO.setLastName(user.get().getLastName());
                personalDetailsDTO.setDob(user.get().getDob());
                return personalDetailsDTO;
            }else {
                throw new LoginApplicationException("Incorect Password");
            }
        }

    }

    @Override
    public String addUser(UserDTO userDTO) throws LoginApplicationException {
        Optional<UserEntity> user = userRepository.findById(userDTO.getUserId());
        if (user.isPresent()){
            throw new LoginApplicationException(environment.getProperty("user.id.present"));
        }else {
            entity.setUserId(userDTO.getUserId());
            entity.setUserPassword(passwordEncoder.encode(userDTO.getUserPassword()));
            entity.setTitle(userDTO.getTitle());
            entity.setFirstName(userDTO.getFirstName());
            entity.setMiddleName(userDTO.getMiddleName());
            entity.setLastName(userDTO.getLastName());
            entity.setDob(userDTO.getDob());
            entity = userRepository.save(entity);
            return entity.getUserId();
        }

    }
}
