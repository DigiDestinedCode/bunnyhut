package com.senac.bunnyhut.repository;


import com.senac.bunnyhut.entity.BackgroundSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BackgroundSlotRepository extends JpaRepository<BackgroundSlot, Integer> {
//    @Modifying
//    @Transactional
//    @Query("UPDATE Background_Slot p SET p.status = -1 WHERE p.id = :id")
//    void apagadoLogicoBackground_Slot(@Param("id") Integer background_slotId);

    @Query("SELECT p from Backgrounds_Slot p")
    List<BackgroundSlot> listBackgroundSlots();

    @Query("SELECT p from Backgrounds_Slot p where p.id=:id")
    BackgroundSlot obterBackgroundSlotPeloId(@Param("id") Integer background_slotId);
}
