package xmlprocessing.goldeneagle.service.interfaces;

import xmlprocessing.goldeneagle.domain.dto.queryDTO.query2.UsersWithSuccessfullySoldProductsModelViewWrapper;
import xmlprocessing.goldeneagle.domain.dto.queryDTO.query4.UsersWithAtLeastOneProductSoldModelViewWrapper;
import xmlprocessing.goldeneagle.domain.dto.seedDto.UserSeedDTOWrapper;

public interface UserService {

    long getCountOfUsers();

    void seedUsers(UserSeedDTOWrapper userSeedDTOS);

    void setFriends();

    UsersWithSuccessfullySoldProductsModelViewWrapper getSuccessfullySoldProducts();

    UsersWithAtLeastOneProductSoldModelViewWrapper getAllUsersWithAtLeastOneProductSold();

}
