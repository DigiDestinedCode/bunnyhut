package com.senac.bunnyhut.repository;


import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository

public interface InventoryRepository extends JpaRepository<Inventory, Integer> {
    @Modifying
    @Transactional
    @Query("UPDATE Inventory p SET p.status = -1 WHERE p.id = :id")
    void apagadoLogicoInventory(@Param("id") Integer inventoryId);

    @Query("SELECT p from Inventory p WHERE p.status >= 0")
    List<Inventory> listarInventorys();

    @Query("SELECT p from Inventory p where p.id=:id AND p.status >=0")
    Inventory obterInventoryPeloId(@Param("id") Integer inventoryId);
}
