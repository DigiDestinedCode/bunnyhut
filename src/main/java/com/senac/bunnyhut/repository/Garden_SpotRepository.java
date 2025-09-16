package com.senac.bunnyhut.repository;


import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository

public interface Garden_SpotRepository extends JpaRepository<Garden_Spot, Integer> {
    @Modifying
    @Transactional
    @Query("UPDATE Garden_Spot p SET p.status = -1 WHERE p.id = :id")
    void apagadoLogicoGarden_Spot(@Param("id") Integer garden_spotId);

    @Query("SELECT p from Garden_Spot p WHERE p.status >= 0")
    List<Garden_Spot> listarGarden_Spots();

    @Query("SELECT p from Garden_Spot p where p.id=:id AND p.status >=0")
    Garden_Spot obterGarden_SpotPeloId(@Param("id") Integer garden_spotId);
}
