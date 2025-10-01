package com.senac.bunnyhut.repository;


import com.senac.bunnyhut.entity.Furniture;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface FurnitureRepository extends JpaRepository<Furniture, Integer> {
//    @Modifying
//    @Transactional
//    @Query("UPDATE Furniture p SET p.status = -1 WHERE p.id = :id")
//    void apagadoLogicoFurniture(@Param("id") Integer furnitureId);

    @Query("SELECT p from Furniture p")
    List<Furniture> listFurnitures();

    @Query("SELECT p from Furniture p where p.id=:id")
    Furniture obterFurniturePeloId(@Param("id") Integer furnitureId);
}
