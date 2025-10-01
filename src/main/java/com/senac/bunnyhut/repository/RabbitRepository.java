package com.senac.bunnyhut.repository;


import com.senac.bunnyhut.entity.Rabbit;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface RabbitRepository extends JpaRepository<Rabbit, Integer> {
//    @Modifying
//    @Transactional
//    @Query("UPDATE Rabbit p SET p.status = -1 WHERE p.id = :id")
//    void apagadoLogicoRabbit(@Param("id") Integer rabbitId);

    @Query("SELECT p from Rabbit p")
    List<Rabbit> listRabbits();

    @Query("SELECT p from Rabbit p where p.id=:id")
    Rabbit obterRabbitPeloId(@Param("id") Integer rabbitId);
}
