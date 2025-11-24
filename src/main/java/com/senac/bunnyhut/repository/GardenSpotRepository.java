package com.senac.bunnyhut.repository;


import com.senac.bunnyhut.entity.Garden_Spot;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface Garden_SpotRepository extends JpaRepository<Garden_Spot, Integer> {
//    @Modifying
//    @Transactional
//    @Query("UPDATE Garden_Spot p SET p.status = -1 WHERE p.id = :id")
//    void apagadoLogicoGarden_Spot(@Param("id") Integer garden_spotId);

    @Query("SELECT p from Garden_Spot p")
    List<Garden_Spot> listGarden_Spots();

    @Query("SELECT p from Garden_Spot p where p.id=:id")
    Garden_Spot obterGarden_SpotPeloId(@Param("id") Integer garden_spotId);
}
