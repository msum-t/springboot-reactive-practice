package learning.user.service.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class TransactionResponseDTO {
    private Integer userId;
    private Double amt;
    private TransactionEnum  status;
}
