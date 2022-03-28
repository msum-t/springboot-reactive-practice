package learning.user.service.util;

import learning.user.service.dto.TransactionEnum;
import learning.user.service.dto.TransactionRequestDTO;
import learning.user.service.dto.TransactionResponseDTO;
import learning.user.service.dto.UserDTO;
import learning.user.service.entity.User;
import learning.user.service.entity.UserTransaction;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

public class EntityToDTO {

    public static UserDTO toDTO(User user){
        UserDTO userDTO=new UserDTO();
        BeanUtils.copyProperties(user,userDTO);
        return userDTO;
    }

    public static User toEntity(UserDTO userDto){
        User user=new User();
        BeanUtils.copyProperties(userDto,user);
        return user;
    }


    public static UserTransaction toTxEntity(TransactionRequestDTO txDto){
        UserTransaction userTx=new UserTransaction();
     userTx.setUserId(txDto.getUserId());
     userTx.setAmt(txDto.getAmt());
     userTx.setLocalDateTime(LocalDateTime.now());
     return userTx;
    }

    public static TransactionResponseDTO toTxDTO(TransactionRequestDTO txResponse, TransactionEnum transactionEnum){
        TransactionResponseDTO resDTO=new TransactionResponseDTO();

        resDTO.setAmt(txResponse.getAmt());
        resDTO.setUserId(txResponse.getUserId());
        resDTO.setStatus(transactionEnum);

        return resDTO;
    }



}
