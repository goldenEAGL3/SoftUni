package xmlprocessing.goldeneagle.repository;

import xmlprocessing.goldeneagle.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findAllByPriceAfterAndPriceBeforeAndBuyerIsNullOrderByPrice(BigDecimal priceAfter, BigDecimal priceBefore);

}
