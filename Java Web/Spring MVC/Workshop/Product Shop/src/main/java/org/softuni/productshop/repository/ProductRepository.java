package org.softuni.productshop.repository;

import org.softuni.productshop.domain.entity.Category;
import org.softuni.productshop.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

    @Query("SELECT p FROM Product p WHERE :category MEMBER of p.categories")
    List<Product> findAllByCategory(Category category);
}
