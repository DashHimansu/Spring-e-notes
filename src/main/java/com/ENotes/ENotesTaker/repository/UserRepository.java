package com.ENotes.ENotesTaker.repository;

import com.ENotes.ENotesTaker.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserInfo, Integer> {

    public UserInfo findByEmail(String email);

}
