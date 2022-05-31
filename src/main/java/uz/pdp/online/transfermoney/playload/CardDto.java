package uz.pdp.online.transfermoney.playload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardDto {
    private Integer id;
    private String username;
    private String number;
    private float balance;
    private Date expiredDate;
    private boolean active;
}
