package com.example.service;

import com.example.dto.RegistrationDto;
import com.example.enums.Role;
import com.example.exception.UserNotFoundException;
import com.example.exception.UsersNotFoundException;
import com.example.model.User;
import com.example.repository.UserRepository;
import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Data
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public List<User> findAll() {
        List<User> foundUsers = new ArrayList<>();
        if (userRepository.findAll().isEmpty()) {
            throw new UsersNotFoundException("Apparently there are no users yet");
        }
        foundUsers = userRepository.findAll();
        return foundUsers;
    }

    @Override
    public User findById(long id) {
        User foundUser = new User();
        if (userRepository.findById(id).isPresent()) {
            foundUser = userRepository.findById(id).get();
        } else {
            throw new UserNotFoundException("User is not found");
        }
        return foundUser;
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    public boolean register(RegistrationDto userDto) {

        Optional<User> userFromDb = userRepository.findByUsername(userDto.getUsername());
        if (!userFromDb.isPresent()) {

            User user = new User();
            user.setId(userDto.getId());
            user.setUsername(userDto.getUsername());
            user.setFirstname(userDto.getFirstname());
            user.setLastname(userDto.getLastname());
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
            user.setActive(true);
            user.setRoles(Collections.singleton(Role.USER));
            userRepository.save(user);

            return true;
        }
        return false;
    }

    @Override
    public void removeById(long id) {

        if (findById(id) != null) {
            userRepository.removedById(id);
        } else {
            throw new UserNotFoundException("User is not Found");
        }

    }

    @Override
    public void resetById(Long id) {
        if (findById(id) != null) {
            userRepository.resetById(id);
        } else {
            throw new UserNotFoundException("User is not Found");
        }
    }

    @Override
    public List<User> findAllByIsDeletedTrue() {
        List<User> foundUsers = new ArrayList<>();
        if (userRepository.findAllByIsDeletedTrue().isEmpty()) {
            throw new UsersNotFoundException("Apparently there are no users yet");
        }

        foundUsers = userRepository.findAllByIsDeletedTrue();
        return foundUsers;
    }

    @Override
    public List<User> findAllByIsDeletedFalse() {
        List<User> foundUsers = new ArrayList<>();
        if (userRepository.findAllByIsDeletedFalse().isEmpty()) {
            throw new UsersNotFoundException("Apparently there are no users yet");
        }

        foundUsers = userRepository.findAllByIsDeletedFalse();
        return foundUsers;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> byUsername = userRepository.findByUsername(username);
        User userByUsername = new User();
        if (byUsername.isPresent()) {
            userByUsername = byUsername.get();
        }
        return userByUsername;
    }
}
