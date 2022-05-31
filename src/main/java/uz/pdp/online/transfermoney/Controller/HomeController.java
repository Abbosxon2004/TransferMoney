package uz.pdp.online.transfermoney.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class HomeController {

    @GetMapping
    public String getInformationAbout() {
        String information = "Hello everyone.You can use this links:" +
                "\n/api/auth/login   --  'login to system'" +
                "\n/api/send  --  'sending money to another card'" +
                "\n/api/income  --  'show income history for you'" +
                "\n/api/outcome  -- 'show outcome history for you'";
        return information;
    }
}
