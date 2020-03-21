package xmlprocessing.goldeneagle.service;

import xmlprocessing.goldeneagle.domain.dto.queryDTO.query2.ProductsSoldModelView;
import xmlprocessing.goldeneagle.domain.dto.queryDTO.query2.UsersWithSuccessfullySoldProductsModelView;
import xmlprocessing.goldeneagle.domain.dto.queryDTO.query2.UsersWithSuccessfullySoldProductsModelViewWrapper;
import xmlprocessing.goldeneagle.domain.dto.queryDTO.query2.SoldProductsModelView;
import xmlprocessing.goldeneagle.domain.dto.queryDTO.query4.UsersWithAtLeastOneProductSoldModelView;
import xmlprocessing.goldeneagle.domain.dto.queryDTO.query4.UsersWithAtLeastOneProductSoldModelViewWrapper;
import xmlprocessing.goldeneagle.domain.dto.seedDto.UserSeedDTO;
import xmlprocessing.goldeneagle.domain.dto.seedDto.UserSeedDTOWrapper;
import xmlprocessing.goldeneagle.domain.entity.Product;
import xmlprocessing.goldeneagle.domain.entity.User;
import xmlprocessing.goldeneagle.repository.UserRepository;
import xmlprocessing.goldeneagle.service.interfaces.UserService;
import xmlprocessing.goldeneagle.utils.interfaces.DtoConverter;
import xmlprocessing.goldeneagle.utils.interfaces.RandomUtil;
import xmlprocessing.goldeneagle.utils.interfaces.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ValidationUtil validationUtil;
    private final DtoConverter dtoConverter;
    private final RandomUtil randomUtil;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           ValidationUtil validationUtil,
                           DtoConverter dtoConverter, RandomUtil randomUtil) {
        this.userRepository = userRepository;
        this.validationUtil = validationUtil;
        this.dtoConverter = dtoConverter;
        this.randomUtil = randomUtil;
    }

    @Override
    public long getCountOfUsers() {
        return this.userRepository.count();
    }

    @Override
    public void seedUsers(UserSeedDTOWrapper userSeedDTOS) {

        for (UserSeedDTO seedDTO : userSeedDTOS.getUsers()) {
            if (!this.validationUtil.isValid(seedDTO)) {
                System.out.println(this.validationUtil.violationMessage(seedDTO));
                continue;
            }

            User user = this.dtoConverter.convert(seedDTO, User.class);
            this.userRepository.saveAndFlush(user);
        }
    }

    @Override
    public void setFriends() {
        List<User> users = this.userRepository.findAll();
        for (User user : users) {
            int randomNumber = this.randomUtil.getRandomNumber(users.size());

            for (int i = 0; i < randomNumber; i++) {
                int randomUserId = this.randomUtil.getRandomNumber(users.size());
                User foundUser;
                foundUser = this.getUserFromCurrentList(users, randomUserId);
                user.addFriend(foundUser);
                foundUser.addFriend(user);
            }
        }
        for (User value : users) {
            this.userRepository.save(value);
        }
    }

    @Override
    public UsersWithSuccessfullySoldProductsModelViewWrapper getSuccessfullySoldProducts() {
        List<User> users = getCorrectUsersQuery2();

        List<UsersWithSuccessfullySoldProductsModelView> result = new ArrayList<>();
        users.forEach(user -> {
            List<Product> productsWithBuyers = user
                    .getSoldProducts()
                    .stream()
                    .filter((product -> product.getBuyer() != null))
                    .collect(Collectors.toList());


            List<ProductsSoldModelView> productsSoldView = new ArrayList<>();
            for (Product prod : productsWithBuyers) {
                ProductsSoldModelView productConverted = this.dtoConverter.convert(prod, ProductsSoldModelView.class);
                productsSoldView.add(productConverted);
            }
            SoldProductsModelView soldProductsModelView = new SoldProductsModelView();
            soldProductsModelView.setProducts(productsSoldView);
            UsersWithSuccessfullySoldProductsModelView convertedUser = this.dtoConverter
                    .convert(user, UsersWithSuccessfullySoldProductsModelView.class);
            convertedUser.setSoldProducts(soldProductsModelView);
            result.add(convertedUser);
        });
        UsersWithSuccessfullySoldProductsModelViewWrapper userResult = new UsersWithSuccessfullySoldProductsModelViewWrapper();
        userResult.setUsers(result);
        return userResult;
    }

    @Override
    public UsersWithAtLeastOneProductSoldModelViewWrapper getAllUsersWithAtLeastOneProductSold() {
        List<User> users = this.userRepository
                .findAll()
                .stream()
                .filter(user -> user.getSoldProducts().size() > 0)
                .sorted((a, b) -> {
                    int sort = b.getSoldProducts().size() - a.getSoldProducts().size();
                    if (sort == 0) {
                        sort = a.getLastName().compareTo(b.getLastName());
                    }

                    return sort;
                })
                .collect(Collectors.toList());

        List<UsersWithAtLeastOneProductSoldModelView> resultUserList = new ArrayList<>();
        for (User user : users) {

            UsersWithAtLeastOneProductSoldModelView resultUser = this.dtoConverter.convert(user, UsersWithAtLeastOneProductSoldModelView.class);
            resultUser.getSoldProducts().setCount(resultUser.getSoldProducts().getProducts().size());
            resultUserList.add(resultUser);

        }
        UsersWithAtLeastOneProductSoldModelViewWrapper resultWrapper = new UsersWithAtLeastOneProductSoldModelViewWrapper();
        resultWrapper.setUsers(resultUserList);
        resultWrapper.setUsersCount(resultUserList.size());

        return resultWrapper;
    }

    private List<User> getCorrectUsersQuery2() {
        return this.userRepository
                .findAll()
                .stream()
                .filter(user -> {
                    boolean atLeastOneProduct = user.getSoldProducts().size() > 0;
                    boolean atLeastOneBuyer = false;
                    for (Product soldProduct : user.getSoldProducts()) {
                        if (soldProduct.getBuyer() != null) {
                            atLeastOneBuyer = true;
                            break;
                        }
                    }
                    return atLeastOneProduct && atLeastOneBuyer;
                })
                .sorted((a, b) -> {
                    int sort = a.getLastName().compareTo(b.getLastName());
                    if (sort == 0) {
                        if (a.getFirstName() == null) {
                            sort = -1;
                        } else if (b.getFirstName() == null) {
                            sort = 1;
                        } else {
                            sort = a.getFirstName().compareTo(b.getFirstName());
                        }
                    }
                    return sort;
                })
                .collect(Collectors.toList());
    }

    private User getUserFromCurrentList(List<User> users, int randomUserId) {
        for (User user : users) {
            if (user.getId() == randomUserId) {
                return user;
            }
        }
        return null;
    }

}
