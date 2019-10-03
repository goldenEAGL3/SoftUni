package softuni.jsonexercise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.jsonexercise.domain.entities.Product;

import java.math.BigDecimal;
import java.util.Set;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Set<Product> findAllByPriceAfterAndPriceBeforeAndBuyerIsNullOrderByPrice(BigDecimal priceAfter, BigDecimal priceBefore);
}
