package com.pangsoramdepo.rbac.repo;

import java.util.Optional;

import com.pangsoramdepo.rbac.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    
    Optional<User> findOneByUsername(String username);

}
