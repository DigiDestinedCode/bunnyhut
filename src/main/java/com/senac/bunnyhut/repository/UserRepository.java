package com.senac.bunnyhut.repository;


import com.senac.bunnyhut.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface UserRepository extends JpaRepository<User, Integer> {
    @Modifying
    @Transactional
    @Query("UPDATE User p SET p.status = -1 WHERE p.id = :id")
    void logicalDeleteUser(@Param("id") Integer userId);

    @Query("SELECT p from User p WHERE p.status >= 0")
    List<User> listUsers();

    @Query("SELECT p from User p where p.id=:id AND p.status >=0")
    User getUserById(@Param("id") Integer userId);

    Optional<User> findByEmail(String email);
}