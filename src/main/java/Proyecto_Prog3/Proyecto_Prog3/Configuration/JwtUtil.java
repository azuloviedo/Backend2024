package Proyecto_Prog3.Proyecto_Prog3.Configuration;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.tomcat.util.buf.UDecoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import jakarta.servlet.http.HttpServletRequest;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.SignatureException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import io.jsonwebtoken.Jws;
import org.springframework.util.StringUtils;
import java.time.Instant;
import java.time.chrono.*;


@Component
public class JwtUtil {

    private final String secret = "2b44b0b00fd822d8ce753e54dac3dc4e06c2725f7db930f3b9924468b53194dbccdbe23d7baa5ef5fbc414ca4b2e64700bad60c5a7c45eaba56880985582fba4";
    private final Long expiration = 3600l;

   /* public String generateToken(String username, Long id, String rol) {
        return Jwts.builder()
                .setSubject(username) // Establece el subject
                .claim("id", id) // Establece las Claims: información del usuario en el token.
                .claim("rol", rol)
                .setIssuedAt(new Date(System.currentTimeMillis())) // Fecha de emisión del token.
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // Fecha de expiración del token.
                .signWith(SignatureAlgorithm.HS256, secret) // Firma el token.
                .compact(); // Crea una cadena JWT con la configuración establecida.
    }*/

    public String generateToken(String username, Long id, String rol) {
        return Jwts.builder()
                .setSubject(username)
                .claim("id", id)
                .claim("rol", rol)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) // 24 horas
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }



    /*public String generateToken(String username, Long id, String rol) {
        return Jwts.builder()
                .setSubject(username) // Establece el subject
                .claim("id", id) //Establece las Claims: información del usuario en el token.
                .claim("rol", rol)
                .setIssuedAt(new Date(System.currentTimeMillis())) //Fecha de emisión del token.
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) //Fecha de expiracion del token.
                .signWith(SignatureAlgorithm.HS256, secret.getBytes()) //Firma el token.
                .compact(); //Crea una cadena JWT con la configuración establecida.
    }*/

    public String getToken(HttpServletRequest httpServletRequest) {
        String barrerToken = httpServletRequest.getHeader("Authorization"); //Obtiene el contenido del Authorization Header
        System.out.println("barrertoken");
        System.out.println(barrerToken);
        if(StringUtils.hasText(barrerToken) && barrerToken.startsWith("Bearer ")) { // Verifica que sea un token JWT
            return barrerToken.substring(7); //Obtiene la cadena del token.
        }
        return null;
    }

    /*public boolean validateToken(String token) {
        try {
            // Si puedo obtener las claims entonces el token es valido
            Jws<Claims> claimsJws =  Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            if(isTokenExpired(token)) {
                System.out.println("Token expirado");
                return false; } // Verfica que no haya expirado.
            return true;
        } catch (Exception e) {
            // Si el token no es valido, puedo realizar alguna acción
            System.out.println("Token no válido:");
            System.out.println(e);
        }
        return false;
    }*/
    public boolean validateToken(String token) {
        try {
            // Si puedo obtener las claims entonces el token es válido
            Jws<Claims> claimsJws = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token);
            if (isTokenExpired(token)) {
                System.out.println("Token expirado");
                return false;
            }
            return true;
        } catch (SignatureException e) {
            // Token tiene una firma no válida
            System.out.println("Firma del token no válida:");
            System.out.println(e);
        } catch (ExpiredJwtException e) {
            // Token ha expirado
            System.out.println("Token expirado:");
            System.out.println(e);
        } catch (Exception e) {
            // Si el token no es válido, puedo realizar alguna acción
            System.out.println("Token no válido:");
            System.out.println(e);
        }
        return false;
    }


    public Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    /*public Long extractId(String token) {
        return Long.parseLong((String) extractAllClaims(token).get("id"));
    }*/

    public Long extractId(String token) {
        return extractAllClaims(token).get("id", Long.class);
    }

    public String extractRol(String token) {
        return extractAllClaims(token).get("rol", String.class);
    }

    public Date extractExpiration(String token) {
        return extractAllClaims(token).getExpiration();
    }

    public Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

}
