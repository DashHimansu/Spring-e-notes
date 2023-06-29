package com.ENotes.ENotesTaker.config;

import com.ENotes.ENotesTaker.entity.UserInfo;
import com.ENotes.ENotesTaker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserInfoServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository repository;

    public UserInfoServiceImpl() {
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo user = repository.findByEmail(username);
        if(user==null){
            throw new UsernameNotFoundException("User not exist");
        }
        else{
            CustomUserInfo customUserInfo = new CustomUserInfo(user);
            return customUserInfo;
        }
    }
}
