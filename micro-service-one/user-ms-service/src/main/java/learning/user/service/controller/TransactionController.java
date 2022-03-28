package learning.user.service.controller;

import learning.user.service.dto.TransactionRequestDTO;
import learning.user.service.dto.TransactionResponseDTO;
import learning.user.service.entity.UserTransaction;
import learning.user.service.service.TxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("user/transaction")
public class TransactionController {


    @Autowired
    private TxService txService;

    @PostMapping
    public Mono<TransactionResponseDTO> createTx(@RequestBody  Mono<TransactionRequestDTO> transactionRequestDTO){
        return transactionRequestDTO.flatMap(this.txService::createTransaction);
    }

    @GetMapping("{id}")
    public  Mono<ResponseEntity<UserTransaction>> getTx(@PathVariable Integer id){
        return txService.userTx(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
