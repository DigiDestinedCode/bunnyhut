package com.senac.bunnyhut.repository;


import com.senac.bunnyhut.entity.Item;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface ItemRepository extends JpaRepository<Item, Integer> {
    @Modifying
    @Transactional
    @Query("UPDATE Item p SET p.status = -1 WHERE p.id = :id")
    void logicalDeleteItem(@Param("id") Integer itemId);

    @Query("SELECT p from Item p")
    List<Item> listItems();

    @Query("SELECT p from Item p where p.id=:id")
    Item getItemById(@Param("id") Integer itemId);
}