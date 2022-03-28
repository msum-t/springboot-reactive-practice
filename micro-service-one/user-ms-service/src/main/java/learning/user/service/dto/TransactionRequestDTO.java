package learning.user.service.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class TransactionRequestDTO {
    private Integer userId;
    private Double amt;
}
