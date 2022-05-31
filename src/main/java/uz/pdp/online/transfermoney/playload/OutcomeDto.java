package uz.pdp.online.transfermoney.playload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OutcomeDto {
    private Integer fromCardId;
    private Integer toCardId;
    private Integer amount;
    private Date date;
    private float commisionAmount;
}
