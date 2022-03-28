package learning.user.service.service;

import learning.user.service.dto.TransactionEnum;
import learning.user.service.dto.TransactionRequestDTO;
import learning.user.service.dto.TransactionResponseDTO;
import learning.user.service.entity.UserTransaction;
import learning.user.service.repository.UserRepo;
import learning.user.service.repository.UserTxRepo;
import learning.user.service.util.EntityToDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class TxService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private UserTxRepo userTxRepo;

    public Mono<TransactionResponseDTO> createTransaction(TransactionRequestDTO tx) {
        return this.userRepo.updateUserBal(tx.getUserId(), tx.getAmt())
                .filter(Boolean::booleanValue)
                .map(p -> EntityToDTO.toTxEntity(tx))
                .flatMap(this.userTxRepo::save)
                .map(p -> EntityToDTO.toTxDTO(tx, TransactionEnum.APPROVED))
                .defaultIfEmpty(EntityToDTO.toTxDTO(tx, TransactionEnum.DECLINE));
    }

    public  Mono<UserTransaction> userTx(Integer id){
        return this.userTxRepo.findByUserId(id);
    }

}
