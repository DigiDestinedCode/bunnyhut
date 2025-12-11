package com.senac.bunnyhut.repository;


import com.senac.bunnyhut.entity.Plant;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface PlantRepository extends JpaRepository<Plant, Integer> {
//    @Modifying
//    @Transactional
//    @Query("UPDATE Plant p SET p.status = -1 WHERE p.id = :id")
//    void logicalDeletePlant(@Param("id") Integer plantId);

    @Query("SELECT p from Plant p")
    List<Plant> listPlants();

    @Query("SELECT p from Plant p where p.id=:id")
    Plant getPlantById(@Param("id") Integer plantId);
}