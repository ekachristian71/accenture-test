package com.h2.demo.repository;

import com.h2.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.socialSecurityNumber = ?1")
    Optional<User> findBySocialSecurityNumber(String socialSecurityNumber);

}
