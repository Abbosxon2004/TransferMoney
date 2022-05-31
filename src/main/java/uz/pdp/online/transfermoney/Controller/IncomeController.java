package uz.pdp.online.transfermoney.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.online.transfermoney.Repository.IncomeRepository;
import uz.pdp.online.transfermoney.Security.JwtProvider;
import uz.pdp.online.transfermoney.Entity.Income;
import uz.pdp.online.transfermoney.Service.IncomeService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/income")
public class IncomeController {
    @Autowired
    JwtProvider jwtProvider;
    @Autowired
    IncomeService incomeService;

    @GetMapping
    public ResponseEntity getIncomes(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        token = token.substring(7);
        String username = jwtProvider.getUsernameFromToken(token);
        List<Income> incomeList = incomeService.getIncomes(username);
        return ResponseEntity.ok(incomeList);
    }
}
