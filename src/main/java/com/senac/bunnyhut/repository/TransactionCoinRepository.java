package com.senac.bunnyhut.repository;


import com.senac.bunnyhut.entity.TransactionCoin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface TransactionCoinRepository extends JpaRepository<TransactionCoin, Integer> {
//    @Modifying
//    @Transactional
//    @Query("UPDATE Transaction_Coin p SET p.status = -1 WHERE p.id = :id")
//    void logicalDeleteTransactionCoin(@Param("id") Integer transactionCoinId);

    @Query("SELECT p from TransactionCoin p")
    List<TransactionCoin> listTransactionCoins();

    @Query("SELECT p from TransactionCoin p where p.id=:id")
    TransactionCoin getTransactionCoinById(@Param("id") Integer transactionCoinId);
}