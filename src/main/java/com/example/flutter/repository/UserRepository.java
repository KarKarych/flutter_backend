package com.example.flutter.repository;

import com.example.flutter.entity.FlutterUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<FlutterUser, UUID> {

    List<FlutterUser> findByOrderByLastNameDesc();

    Optional<FlutterUser> findByLogin(String login);

    @Modifying
    @Query("""
            UPDATE  FlutterUser u
            SET     u.balance = :balance
            WHERE   u.id = :id
            """)
    void updateBalanceById(Integer balance, UUID id);
}