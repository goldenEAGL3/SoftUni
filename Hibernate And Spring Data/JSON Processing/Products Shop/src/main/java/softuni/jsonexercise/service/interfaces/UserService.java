package softuni.jsonexercise.service.interfaces;

import softuni.jsonexercise.domain.dto.queryDTO.query2.UsersWithAtLeastOneProductSoldModelView;
import softuni.jsonexercise.domain.dto.queryDTO.query4.UserViewModelWrapperDtoQuery4;
import softuni.jsonexercise.domain.dto.seedDTO.UserSeedDTO;

import java.util.List;

public interface UserService {
    void seedUsers(UserSeedDTO[] userSeedDTO);

    long getCountOfUsers();

    void setFriends();

    List<UsersWithAtLeastOneProductSoldModelView> getAllUsersWithAtLeastOneProductSold();

    UserViewModelWrapperDtoQuery4 getAllUsersInfo();
}
