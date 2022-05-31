package uz.pdp.online.transfermoney.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import uz.pdp.online.transfermoney.Service.MyAuthService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    JwtProvider jwtProvider;
    @Autowired
    MyAuthService myAuthService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        String token = request.getHeader("Authorization");

        if (token != null && token.startsWith("Bearer")) {
            token = token.substring(7);

            // tokenni yaroqli ekanligi tekshiriladi.
            boolean validateToken = jwtProvider.validateToken(token);

            if (validateToken) {

                // token orqali username olinadi
                String usernameFromToken = jwtProvider.getUsernameFromToken(token);

                //Username orqali userning malumoti yuklanadi-- userdetails ko`rinishida
                UserDetails userDetails = myAuthService.loadUserByUsername(usernameFromToken);

                //user uchun uning malumotlari asosida authentication yaratadi
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                //Sistemaga kim kirganligini authentication orqali o`rnatdik
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

            }
        }

        // tokenni filterlay olmasa yoki ushbu userdagi user topilmasaxatolik o`rniga kelgan so`rovni o`zini qaytaradi;
        filterChain.doFilter(request, response);
    }

}
