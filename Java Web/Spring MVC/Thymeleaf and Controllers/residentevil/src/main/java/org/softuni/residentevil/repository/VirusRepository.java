package org.softuni.residentevil.repository;

import org.softuni.residentevil.domain.entity.Virus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VirusRepository extends JpaRepository<Virus, String> {

}
