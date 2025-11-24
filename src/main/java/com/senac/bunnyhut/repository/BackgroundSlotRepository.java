package com.senac.bunnyhut.repository;


import com.senac.bunnyhut.entity.Background_Slot;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Background_SlotRepository extends JpaRepository<Background_Slot, Integer> {
//    @Modifying
//    @Transactional
//    @Query("UPDATE Background_Slot p SET p.status = -1 WHERE p.id = :id")
//    void apagadoLogicoBackground_Slot(@Param("id") Integer background_slotId);

    @Query("SELECT p from Background_Slot p")
    List<Background_Slot> listBackground_Slots();

    @Query("SELECT p from Background_Slot p where p.id=:id")
    Background_Slot obterBackground_SlotPeloId(@Param("id") Integer background_slotId);
}
