package com.exam.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.model.Role;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    
}
