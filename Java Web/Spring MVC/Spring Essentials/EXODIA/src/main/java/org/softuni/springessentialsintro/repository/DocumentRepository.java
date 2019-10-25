package org.softuni.springessentialsintro.repository;

import org.softuni.springessentialsintro.domain.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document, String> {
}
