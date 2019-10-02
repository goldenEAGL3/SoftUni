package softuni.jsonexercise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.jsonexercise.domain.entities.Category;

import java.util.Set;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Category findById(int id);


    @Query(value = "SELECT c.name, COUNT(p.id) as 'product_count',\n" +
            "       AVG(p.price) as 'average_price',\n" +
            "       SUM(p.price) as 'total_revenue'\n" +
            "FROM categories c\n" +
            "JOIN products_categories pc on c.id = pc.category_id\n" +
            "JOIN products p on pc.product_id = p.id\n" +
            "GROUP BY c.name\n" +
            "ORDER BY product_count DESC", nativeQuery = true)
    Set<Object[]> categoriesByProductCount();
}
