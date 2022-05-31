package uz.pdp.online.transfermoney.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.online.transfermoney.Security.JwtProvider;
import uz.pdp.online.transfermoney.Entity.model.ApiResponse;
import uz.pdp.online.transfermoney.Entity.model.TransferingModel;
import uz.pdp.online.transfermoney.Service.CardService;
import uz.pdp.online.transfermoney.Service.IncomeService;
import uz.pdp.online.transfermoney.Service.OutcomeService;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/api/send")
public class TransferMoney {

    @Autowired
    JwtProvider jwtProvider;
    @Autowired
    CardService cardService;
    @Autowired
    IncomeService incomeService;
    @Autowired
    OutcomeService outcomeService;

    @PostMapping
    public ResponseEntity transfer(HttpServletRequest request, @RequestBody TransferingModel transferingModel) {
        String token = request.getHeader("Authorization");
        token = token.substring(7);
        String username = jwtProvider.getUsernameFromToken(token);

        cardService.addCardDefaults();

        ApiResponse apiResponse = cardService.sendMoneyEditCardInformation(username, transferingModel);
        if (apiResponse.isSuccess()) {
            incomeService.addIncome(username, transferingModel);
            outcomeService.addOutcome(username, transferingModel);
            return ResponseEntity.ok(apiResponse);
        }
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(apiResponse);

    }

}
