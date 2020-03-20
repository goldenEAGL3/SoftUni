package jsonprocessing.goldeneagle.utils.interfaces;

import jsonprocessing.goldeneagle.domain.entity.Category;
import jsonprocessing.goldeneagle.domain.entity.User;

import java.util.Set;

public interface RandomUtil {

    int getRandomNumber(long value);

    User getRandomUser(int randomId);

    Set<Category> getRandomSetOfCategories();
}
