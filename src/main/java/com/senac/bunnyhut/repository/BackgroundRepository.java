package com.senac.bunnyhut.repository;


import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository

public interface BackgroundRepository extends JpaRepository<Background, Integer> {
    @Modifying
    @Transactional
    @Query("UPDATE Background p SET p.status = -1 WHERE p.id = :id")
    void apagadoLogicoBackground(@Param("id") Integer backgroundId);

    @Query("SELECT p from Background p WHERE p.status >= 0")
    List<Background> listarBackgrounds();

    @Query("SELECT p from Background p where p.id=:id AND p.status >=0")
    Background obterBackgroundPeloId(@Param("id") Integer backgroundId);
}
