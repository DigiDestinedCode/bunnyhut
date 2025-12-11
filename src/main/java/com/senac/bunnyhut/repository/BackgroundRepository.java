package com.senac.bunnyhut.repository;


import com.senac.bunnyhut.entity.Background;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BackgroundRepository extends JpaRepository<Background, Integer> {
//    @Modifying
//    @Transactional
//    @Query("UPDATE Background p SET p.status = -1 WHERE p.id = :id")
//    void logicalDeleteBackground(@Param("id") Integer backgroundId);

    @Query("SELECT p from Background p")
    List<Background> listBackgrounds();

    @Query("SELECT p from Background p where p.id=:id")
    Background getBackgroundById(@Param("id") Integer backgroundId);
}