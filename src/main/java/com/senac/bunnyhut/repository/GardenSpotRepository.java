package com.senac.bunnyhut.repository;


import com.senac.bunnyhut.entity.GardenSpot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface GardenSpotRepository extends JpaRepository<GardenSpot, Integer> {
//    @Modifying
//    @Transactional
//    @Query("UPDATE Garden_Spot p SET p.status = -1 WHERE p.id = :id")
//    void apagadoLogicoGarden_Spot(@Param("id") Integer garden_spotId);

    @Query("SELECT p from Garden_Spot p")
    List<GardenSpot> listGardenSpots();

    @Query("SELECT p from Garden_Spot p where p.id=:id")
    GardenSpot obterGardenSpotPeloId(@Param("id") Integer garden_spotId);
}
