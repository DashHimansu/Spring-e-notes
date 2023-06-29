package com.ENotes.ENotesTaker.service;

import com.ENotes.ENotesTaker.entity.UserInfo;
import com.ENotes.ENotesTaker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {


    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;


    public UserInfo registerUser(UserInfo user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_USER");
        UserInfo userInfo = userRepository.save(user);
        return userInfo;
    }

    public UserInfo updateUser(UserInfo user) {

        Optional<UserInfo> Olduser = userRepository.findById(user.getId());
        UserInfo updateUser = null;
        if (Olduser != null) {
            user.setPassword(Olduser.get().getPassword());
            user.setRole(Olduser.get().getRole());
            user.setEmail(Olduser.get().getEmail());

            updateUser = userRepository.save(user);

        }
        return updateUser;
    }

    public UserInfo findByEmail(String email) {
        UserInfo user = userRepository.findByEmail(email);
        return user;
    }
}
