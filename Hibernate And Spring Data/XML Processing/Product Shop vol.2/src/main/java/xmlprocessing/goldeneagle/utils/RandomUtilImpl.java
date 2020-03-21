package xmlprocessing.goldeneagle.utils;

import xmlprocessing.goldeneagle.domain.entity.Category;
import xmlprocessing.goldeneagle.domain.entity.User;
import xmlprocessing.goldeneagle.repository.CategoryRepository;
import xmlprocessing.goldeneagle.repository.UserRepository;
import xmlprocessing.goldeneagle.utils.interfaces.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Component
public class RandomUtilImpl implements RandomUtil {


    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    private Random random;

    @Autowired
    public RandomUtilImpl(CategoryRepository categoryRepository, UserRepository userRepository) {
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
        this.random = new Random();
    }

    @Override
    public int getRandomNumber(long value) {
        return this.random.nextInt((int) value) + 1;
    }

    @Override
    public User getRandomUser(int randomId) {
        User user = null;

        if (randomId % 3 == 0) {
            user = this.userRepository.findUserById(randomId);
        }
        return user;
    }

    @Override
    public Set<Category> getRandomSetOfCategories() {
        Set<Category> categories = new HashSet<>();
        int randomNumber = this.getRandomNumber(this.categoryRepository.count());
        for (int i = 0; i < randomNumber; i++) {
            int randomCategoryId = this.getRandomNumber(this.categoryRepository.count());
            Category category = this.categoryRepository.findCategoryById(randomCategoryId);
            boolean categoryAdded = false;
            for (Category currentCategory : categories) {
                if (currentCategory.getName().equals(category.getName())) {
                    categoryAdded = true;
                    break;
                }
            }
            if (!categoryAdded) {
                categories.add(category);
            }
        }
        return categories;
    }
}
