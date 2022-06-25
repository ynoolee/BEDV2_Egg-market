package com.devcourse.eggmarket.domain.user.service;

import com.devcourse.eggmarket.domain.user.dto.UserRequest;
import com.devcourse.eggmarket.domain.user.dto.UserResponse;
import com.devcourse.eggmarket.domain.user.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    UserResponse save(UserRequest.Save userRequest);

    UserResponse getByUsername(String userName);

    UserResponse update(String userName, UserRequest.Update userRequest);

    User getUser(String userName);

    boolean deleteById(Long id);
}
