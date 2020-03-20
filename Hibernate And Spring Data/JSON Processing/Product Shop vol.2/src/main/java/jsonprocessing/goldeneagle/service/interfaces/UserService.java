package jsonprocessing.goldeneagle.service.interfaces;

import jsonprocessing.goldeneagle.domain.dto.queryDTO.query2.UsersWithSuccessfullySoldProductsModelView;
import jsonprocessing.goldeneagle.domain.dto.queryDTO.query4.UsersWithAtLeastOneProductSoldModelViewWrapper;
import jsonprocessing.goldeneagle.domain.dto.seedDto.UserSeedDTO;

import java.util.List;

public interface UserService {

    long getCountOfUsers();

    void seedUsers(UserSeedDTO[] userSeedDTOS);

    void setFriends();

    List<UsersWithSuccessfullySoldProductsModelView> getSuccessfullySoldProducts();

    UsersWithAtLeastOneProductSoldModelViewWrapper getAllUsersWithAtLeastOneProductSold();

}
