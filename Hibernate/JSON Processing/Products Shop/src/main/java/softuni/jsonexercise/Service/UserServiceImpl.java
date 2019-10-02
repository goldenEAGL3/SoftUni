package softuni.jsonexercise.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.jsonexercise.Service.interfaces.UserService;
import softuni.jsonexercise.domain.entities.User;
import softuni.jsonexercise.dto.queryDTO.query2.UsersWithAtLeastOneProductSoldModelView;
import softuni.jsonexercise.dto.queryDTO.query4.ProductModelViewDtoQuery4;
import softuni.jsonexercise.dto.queryDTO.query4.SoldProductsModelViewDtoQuery4;
import softuni.jsonexercise.dto.queryDTO.query4.UserViewModelDtoQuery4;
import softuni.jsonexercise.dto.queryDTO.query4.UserViewModelWrapperDtoQuery4;
import softuni.jsonexercise.dto.seedDTO.UserSeedDTO;
import softuni.jsonexercise.repository.UserRepository;
import softuni.jsonexercise.utils.interfaces.DtoConverter;
import softuni.jsonexercise.utils.interfaces.ValidationUtil;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final ValidationUtil validationUtil;
    private final UserRepository userRepository;
    private final DtoConverter dtoConverter;

    @Autowired
    public UserServiceImpl(ValidationUtil validationUtil, UserRepository userRepository, DtoConverter dtoConverter) {
        this.validationUtil = validationUtil;
        this.userRepository = userRepository;
        this.dtoConverter = dtoConverter;
    }

    @Override
    public void seedUsers(UserSeedDTO[] userSeedDTO) {

        for (UserSeedDTO seedDTO : userSeedDTO) {
            if (!this.validationUtil.isValid(seedDTO)) {
                System.out.println(this.validationUtil.violationMessage(seedDTO));
                continue;
            }

            User user = this.dtoConverter.convert(seedDTO, User.class);
            this.userRepository.saveAndFlush(user);
        }
    }

    @Override
    public long getCountOfUsers() {
        return this.userRepository.count();
    }

    @Override
    public void setFriends() {
        List<User> users = this.userRepository.findAll();
        Random random = new Random();
        Map<Integer, User> modifiedUsers = new HashMap<>();
        for (User currentUser : users) {

            int number = random.nextInt((int) this.userRepository.count()) + 1;
            for (int i = 0; i < number; i++) {
                int randomId = random.nextInt((int) this.userRepository.count()) + 1;
                User foundUser = null;
                if (modifiedUsers.containsKey(randomId)) {
                    foundUser = modifiedUsers.get(randomId);
                } else {
                    foundUser = this.userRepository.findUserById(randomId);
                }
                currentUser.addFriend(foundUser);
                foundUser.addFriend(currentUser);

                modifiedUsers.put(currentUser.getId(), currentUser);
                modifiedUsers.put(foundUser.getId(), foundUser);
            }
        }

        for (User value : modifiedUsers.values()) {
            this.userRepository.save(value);
        }
    }

    @Override
    public List<UsersWithAtLeastOneProductSoldModelView> getAllUsersWithAtLeastOneProductSold() {

        List<UsersWithAtLeastOneProductSoldModelView> usersDto = this.userRepository.usersWithAtLeastOneProductSold()
                .stream()
                .map(user -> this.dtoConverter
                        .convert(user, UsersWithAtLeastOneProductSoldModelView.class))
                .collect(Collectors.toList());

        return usersDto;
    }

    @Override
    public UserViewModelWrapperDtoQuery4 getAllUsersInfo() {
        Set<User> userList = this.userRepository.findAllBy();
        List<UserViewModelDtoQuery4> userModelView = this.userRepository.findAllBy().stream()
                .map(user -> {

                    UserViewModelDtoQuery4 modelView = this.dtoConverter.convert(user, UserViewModelDtoQuery4.class);
                    modelView.getSoldProducts().setCount(modelView.getSoldProducts().getProducts().size());


                    return modelView;
                }).collect(Collectors.toList());
        UserViewModelWrapperDtoQuery4 dto = new UserViewModelWrapperDtoQuery4();
        dto.setUserCount(userList.size());
        dto.setUsers(userModelView);
        return dto;
    }
}
