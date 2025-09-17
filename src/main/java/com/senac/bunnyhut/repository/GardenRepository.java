package com.senac.bunnyhut.repository;


import com.senac.bunnyhut.entity.Garden;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface GardenRepository extends JpaRepository<Garden, Integer> {
    @Modifying
    @Transactional
    @Query("UPDATE Garden p SET p.status = -1 WHERE p.id = :id")
    void apagadoLogicoGarden(@Param("id") Integer gardenId);

    @Query("SELECT p from Garden p WHERE p.status >= 0")
    List<Garden> listGardens();

    @Query("SELECT p from Garden p where p.id=:id AND p.status >=0")
    Garden obterGardenPeloId(@Param("id") Integer gardenId);
}
