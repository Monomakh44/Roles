package com.website.web.repositories;

import com.website.web.models.Home;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
@Repository
public interface HomeRepository extends JpaRepository<Home, Long> {
    @Query("from Home home where not(home.stop < :from or home.start > :to)")
    List<Home> findBetween(@Param("from") @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime start, @Param("to") @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime end);
}
