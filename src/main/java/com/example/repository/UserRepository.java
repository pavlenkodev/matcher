package com.example.repository;

import com.example.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Modifying
    @Transactional
    @Query("update User u set u.isDeleted = true where u.id = :id")
    void removedById(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query("update User u set u.isDeleted = false where u.id = :id")
    void resetById(@Param("id") Long id);

    List<User> findAllByIsDeletedFalse();

    List<User> findAllByIsDeletedTrue();

    Optional<User> findByUsername(String username);
}
