package com.senac.bunnyhut.repository;


import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository

public interface FurnitureRepository extends JpaRepository<Furniture, Integer> {
    @Modifying
    @Transactional
    @Query("UPDATE Furniture p SET p.status = -1 WHERE p.id = :id")
    void apagadoLogicoFurniture(@Param("id") Integer furnitureId);

    @Query("SELECT p from Furniture p WHERE p.status >= 0")
    List<Furniture> listarFurnitures();

    @Query("SELECT p from Furniture p where p.id=:id AND p.status >=0")
    Furniture obterFurniturePeloId(@Param("id") Integer furnitureId);
}
