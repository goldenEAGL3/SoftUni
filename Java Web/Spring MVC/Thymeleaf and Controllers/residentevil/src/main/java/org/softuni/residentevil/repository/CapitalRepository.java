package org.softuni.residentevil.repository;

import org.softuni.residentevil.domain.entity.Capital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CapitalRepository extends JpaRepository<Capital, String> {
    @Query("SELECT c FROM Capital c ORDER BY c.name")
    List<Capital> findAllOrderByName();
}
