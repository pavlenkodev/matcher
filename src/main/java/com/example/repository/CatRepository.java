package com.example.repository;

import com.example.model.Cat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CatRepository extends JpaRepository<Cat, Long> {

    @Modifying
    @Transactional
    @Query("update Cat c set c.isDeleted = true where c.id = :id")
    void removedById(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query("update Cat c set c.isDeleted = false where c.id = :id")
    void resetById(@Param("id") Long id);

    List<Cat> findAllByIsDeletedFalse();

    List<Cat> findAllByIsDeletedTrue();

    List<Cat> findTop10ByOrderBySelectedDesc();

}
