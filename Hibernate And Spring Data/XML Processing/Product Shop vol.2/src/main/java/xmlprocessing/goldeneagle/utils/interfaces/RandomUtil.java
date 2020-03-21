package xmlprocessing.goldeneagle.utils.interfaces;

import xmlprocessing.goldeneagle.domain.entity.Category;
import xmlprocessing.goldeneagle.domain.entity.User;

import java.util.Set;

public interface RandomUtil {

    int getRandomNumber(long value);

    User getRandomUser(int randomId);

    Set<Category> getRandomSetOfCategories();
}
