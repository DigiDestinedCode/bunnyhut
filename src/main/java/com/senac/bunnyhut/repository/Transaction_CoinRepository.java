package com.senac.bunnyhut.repository;


import com.senac.bunnyhut.entity.Transaction_Coin;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface Transaction_CoinRepository extends JpaRepository<Transaction_Coin, Integer> {
//    @Modifying
//    @Transactional
//    @Query("UPDATE Transaction_Coin p SET p.status = -1 WHERE p.id = :id")
//    void apagadoLogicoTransaction_Coin(@Param("id") Integer transaction_coinId);

    @Query("SELECT p from Transaction_Coin p")
    List<Transaction_Coin> listTransaction_Coins();

    @Query("SELECT p from Transaction_Coin p where p.id=:id")
    Transaction_Coin obterTransaction_CoinPeloId(@Param("id") Integer transaction_coinId);
}
