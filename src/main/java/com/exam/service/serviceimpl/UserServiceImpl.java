package com.exam.service.serviceimpl;

import java.util.Set;

import com.exam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.Repository.RoleRepository;
import com.exam.Repository.UserRepository;





@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;


    // creating user
    @Override
    public User createUser(User user, Set<UserRole> userRoles) throws Exception {
        // TODO Auto-generated method stub

        User local = this.userRepository.findByUsername(user.getUsername());
        if (local != null) {
            System.out.println("User is already there!");
            throw new Exception("User already present!!");
        } else {
            // user create
            for (UserRole ur : userRoles) {
                        roleRepository.save(ur.getRole());

            }

            user.getUserRoles().addAll(userRoles);
            local = this.userRepository.save(user);
            
             
        }
     return local;
    }

        // getting user by username
    @Override
    public User getUser(String username) {
        // TODO Auto-generated method stub
       return this.userRepository.findByUsername(username);
    }

    @Override
    public User getfirstname(String firstname) {
        return  this.userRepository.findByFirstname(firstname);
    }

    @Override
    public void deleteUser(Long userId) {

        this.userRepository.deleteById(userId);
    }


}
