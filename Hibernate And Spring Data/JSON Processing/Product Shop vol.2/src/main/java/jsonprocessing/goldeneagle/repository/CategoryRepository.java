package jsonprocessing.goldeneagle.repository;

import jsonprocessing.goldeneagle.domain.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Category findCategoryById(int randomCategoryId);
}
