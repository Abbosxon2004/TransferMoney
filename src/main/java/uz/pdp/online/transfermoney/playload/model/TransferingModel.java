package uz.pdp.online.transfermoney.playload.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransferingModel {
    private Integer toCardId;
    private Integer amount;
}
