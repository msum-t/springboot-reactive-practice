package learning.user.service.repository;

import learning.user.service.entity.User;
import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepo extends ReactiveCrudRepository<User,Integer> {

    @Modifying
    @Query("update users  set balance=balance - :amt where id=:userId and balance>=:amt")
    Mono<Boolean> updateUserBal(Integer userId, Double amt);
}
