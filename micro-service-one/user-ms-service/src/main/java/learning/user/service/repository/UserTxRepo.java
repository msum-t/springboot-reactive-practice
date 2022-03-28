package learning.user.service.repository;

import learning.user.service.entity.UserTransaction;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface UserTxRepo extends ReactiveCrudRepository<UserTransaction,Integer> {

   Mono<UserTransaction> findByUserId(Integer userId);

}
