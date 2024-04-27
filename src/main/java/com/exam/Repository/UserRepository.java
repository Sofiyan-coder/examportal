package com.exam.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

        public User findByUsername(String username);
        public User findByFirstname(String firstname);


}