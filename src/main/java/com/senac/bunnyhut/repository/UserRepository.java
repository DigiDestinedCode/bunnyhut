package com.senac.bunnyhut.repository;


import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository

public interface UserRepository extends JpaRepository<User, Integer> {
    @Modifying
    @Transactional
    @Query("UPDATE User p SET p.status = -1 WHERE p.id = :id")
    void apagadoLogicoUser(@Param("id") Integer userId);

    @Query("SELECT p from User p WHERE p.status >= 0")
    List<User> listarUsers();

    @Query("SELECT p from User p where p.id=:id AND p.status >=0")
    User obterUserPeloId(@Param("id") Integer userId);
}
