package uz.pdp.online.transfermoney.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.online.transfermoney.Security.JwtProvider;
import uz.pdp.online.transfermoney.playload.IncomeDto;
import uz.pdp.online.transfermoney.playload.OutcomeDto;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/outcome")
public class OutcomeController {
    @Autowired
    JwtProvider jwtProvider;

    @GetMapping
    public ResponseEntity getOutcomes(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        token = token.substring(7);
        String username = jwtProvider.getUsernameFromToken(token);
        ControlAll controlAll = new ControlAll();
        List<OutcomeDto> outcomeList = controlAll.getOutcomeList(username);
        return ResponseEntity.ok(outcomeList);
    }
}
