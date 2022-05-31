package uz.pdp.online.transfermoney.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.online.transfermoney.Security.JwtProvider;
import uz.pdp.online.transfermoney.playload.model.ApiResponse;
import uz.pdp.online.transfermoney.playload.model.TransferingModel;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/api/send")
public class TransferMoney {

    @Autowired
    JwtProvider jwtProvider;

    @PostMapping
    public ResponseEntity transfer(HttpServletRequest request, @RequestBody TransferingModel transferingModel) {
        String token = request.getHeader("Authorization");
        token = token.substring(7);
        String username = jwtProvider.getUsernameFromToken(token);

        ControlAll controlAll = new ControlAll();
        controlAll.getCardDtoList();
        ApiResponse apiResponse = controlAll.editCardInformation(username, transferingModel);
        if (apiResponse.isSuccess()) {
            controlAll.addIncome(username, transferingModel);
            controlAll.addOutcome(username, transferingModel);
            return ResponseEntity.ok(apiResponse);
        }
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(apiResponse);

    }

}
