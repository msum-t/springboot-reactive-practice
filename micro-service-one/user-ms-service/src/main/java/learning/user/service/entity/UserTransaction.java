package learning.user.service.entity;

import learning.user.service.dto.TransactionEnum;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Data
@ToString
public class UserTransaction {
    @Id
    private Integer id;
    private Integer userId;
    private Double amt;
    private LocalDateTime localDateTime;
}
