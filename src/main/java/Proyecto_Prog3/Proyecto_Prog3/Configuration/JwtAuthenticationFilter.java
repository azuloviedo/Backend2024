package Proyecto_Prog3.Proyecto_Prog3.Configuration;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{
    @Autowired
    private JwtUtil jwtUtilities;

    @Autowired
    private UserDetailsService userDetailsService;


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        // Obtiene el Token JWT de la request
        System.out.println("LA REQUEST ES:");
        System.out.println(request);
        String token = jwtUtilities.getToken(request);
        System.out.println(token);
        System.out.println("AFUERAAAAAA");
        if(token != null && jwtUtilities.validateToken(token)) { //Si existe ese token y es valido
            String username = jwtUtilities.extractUsername(token); //Obtengo el username del token
            System.out.println(username);
            System.out.println("ADENTROOO");

            UserDetails user = userDetailsService.loadUserByUsername(username); //Obtengo el objeto
            if(user != null) { //Si ese usuario existe
                // Creo una representación de la informacion de autenticacion con el username y el listado de credenciales

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(user.getUsername(), null, user.getAuthorities());
                System.out.print(authentication);
                // Agrego el usuario al contexto de seguridad actual. Nos va a permitir recuperarlo durante toda la request.
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

        }
        // Invoca el siguiente filtro de la cadena.
        filterChain.doFilter(request, response);
    }
}
