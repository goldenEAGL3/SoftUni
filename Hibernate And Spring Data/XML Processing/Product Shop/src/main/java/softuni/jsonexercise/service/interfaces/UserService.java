package softuni.jsonexercise.service.interfaces;

import softuni.jsonexercise.domain.dto.queryDTO.query2.UsersWithAtLeastOneProductSoldModelViewWrapper;
import softuni.jsonexercise.domain.dto.queryDTO.query4.UserViewModelWrapperDtoQuery4;
import softuni.jsonexercise.domain.dto.seedDTO.UserSeedModelViewWrapper;

public interface UserService {
    void seedUsers(UserSeedModelViewWrapper userSeedDTO);

    long getCountOfUsers();

    void setFriends();

    UsersWithAtLeastOneProductSoldModelViewWrapper getAllUsersWithAtLeastOneProductSold();

    UserViewModelWrapperDtoQuery4 getAllUsersInfo();
}
