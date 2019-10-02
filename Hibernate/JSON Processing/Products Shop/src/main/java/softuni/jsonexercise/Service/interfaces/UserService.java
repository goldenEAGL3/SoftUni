package softuni.jsonexercise.Service.interfaces;

import softuni.jsonexercise.dto.queryDTO.query2.UsersWithAtLeastOneProductSoldModelView;
import softuni.jsonexercise.dto.queryDTO.query4.UserViewModelWrapperDtoQuery4;
import softuni.jsonexercise.dto.seedDTO.UserSeedDTO;

import java.util.List;

public interface UserService {
    void seedUsers(UserSeedDTO[] userSeedDTO);

    long getCountOfUsers();

    void setFriends();

    List<UsersWithAtLeastOneProductSoldModelView> getAllUsersWithAtLeastOneProductSold();

    UserViewModelWrapperDtoQuery4 getAllUsersInfo();
}
