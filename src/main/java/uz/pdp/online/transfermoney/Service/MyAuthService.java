package uz.pdp.online.transfermoney.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.online.transfermoney.Security.JwtProvider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class MyAuthService implements UserDetailsService {

    @Autowired
    JwtProvider jwtProvider;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        List<User> userList = new ArrayList<>(
                Arrays.asList(
                        new User("anvar", passwordEncoder.encode("anvar"), new ArrayList<>()),
                        new User("salim", passwordEncoder.encode("456"), new ArrayList<>()),
                        new User("karim", passwordEncoder.encode("789"), new ArrayList<>()),
                        new User("nodir", passwordEncoder.encode("852"), new ArrayList<>())
                )
        );

        for (User user : userList) {
            if (user.getUsername().equals(username)) {
                System.out.println(jwtProvider.generateToken(username));
                return user;
            }
        }
        throw new UsernameNotFoundException("User topilmadi");
    }
}
