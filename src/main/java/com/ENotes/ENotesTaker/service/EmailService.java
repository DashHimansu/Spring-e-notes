package com.ENotes.ENotesTaker.service;

import com.ENotes.ENotesTaker.entity.UserInfo;

import java.util.Optional;

public interface EmailService {

    public Optional<String> sendMailToUserEmailId(String to, String subject, String body);
}
