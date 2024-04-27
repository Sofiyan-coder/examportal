package com.exam.service;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.exam.model.User;
import com.exam.model.UserRole;

@Service
public interface UserService {

    // creating user
    public User createUser(User user, Set<UserRole> userRoles) throws Exception;
    // get user byy username
    public User getUser(String username);
    public User getfirstname(String firstname);
    public void deleteUser(Long userId);
    
}
