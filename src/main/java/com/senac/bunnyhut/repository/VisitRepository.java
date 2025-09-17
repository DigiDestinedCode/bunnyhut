package com.senac.bunnyhut.repository;


import com.senac.bunnyhut.entity.Visit;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface VisitRepository extends JpaRepository<Visit, Integer> {
    @Modifying
    @Transactional
    @Query("UPDATE Visit p SET p.status = -1 WHERE p.id = :id")
    void apagadoLogicoVisit(@Param("id") Integer visitId);

    @Query("SELECT p from Visit p WHERE p.status >= 0")
    List<Visit> listVisits();

    @Query("SELECT p from Visit p where p.id=:id AND p.status >=0")
    Visit obterVisitPeloId(@Param("id") Integer visitId);
}
