package com.senac.bunnyhut.repository;


import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface Background_SlotRepository extends JpaRepository<Background_Slot, Integer> {
    @Modifying
    @Transactional
    @Query("UPDATE Background_Slot p SET p.status = -1 WHERE p.id = :id")
    void apagadoLogicoBackground_Slot(@Param("id") Integer background_slotId);

    @Query("SELECT p from Background_Slot p WHERE p.status >= 0")
    List<Background_Slot> listarBackground_Slots();

    @Query("SELECT p from Background_Slot p where p.id=:id AND p.status >=0")
    Background_Slot obterBackground_SlotPeloId(@Param("id") Integer background_slotId);
}
