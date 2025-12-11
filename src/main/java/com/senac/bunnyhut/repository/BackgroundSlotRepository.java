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
//    void logicalDeleteBackgroundSlot(@Param("id") Integer backgroundSlotId);

    @Query("SELECT p FROM BackgroundSlot p")
    List<BackgroundSlot> listBackgroundSlots();

    @Query("SELECT p FROM BackgroundSlot p WHERE p.id = :id")
    BackgroundSlot getBackgroundSlotById(@Param("id") Integer backgroundSlotId);
}