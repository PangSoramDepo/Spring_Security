package com.pangsoramdepo.rbac.repo;

import com.pangsoramdepo.rbac.model.AccessControl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessControlRepository extends JpaRepository<AccessControl, Integer> {
    
}
