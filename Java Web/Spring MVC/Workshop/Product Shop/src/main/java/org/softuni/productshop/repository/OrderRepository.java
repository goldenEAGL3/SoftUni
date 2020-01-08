package org.softuni.productshop.repository;

import org.softuni.productshop.domain.entity.Order;
import org.softuni.productshop.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {
    Set<Order> findAllByCustomer(User customer);
}
