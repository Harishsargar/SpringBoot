package com.example.crud.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.crud.entity.ROLE;
import com.example.crud.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User,String> {
    List<User> findByRole(ROLE role);

    @Query("SELECT u FROM User u WHERE u.createdAt >= :sevenDaysAgo")
    List<User> findUserRegisterInlast7Days(@Param("sevenDaysAgo") LocalDateTime sevenDaysAgoseve);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.role = :newRole WHERE u.role= :oldRole")
    int updateRollForAll(@Param("oldRole") ROLE oldRole, @Param("newRole") ROLE newRole);

}

