package Proyecto_Prog3.Proyecto_Prog3.models;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import ch.qos.logback.core.sift.AbstractDiscriminator;


@Entity
public class Administrador extends Usuario {
    // Constructor
    public Administrador(Long id, String nombreUsuario, String contrasenia, String nombre, String apellido) {
        super(id, nombreUsuario, contrasenia, nombre, apellido);
    }

      public Administrador()
      {

      }

    @Override
    public String getRole() {
        return "Administrador";
    }

    @Override
    public String getPassword() {
        return getContrasenia(); // Retorna la contraseña
    }

    @Override
    public String getUsername() {
        return getNombreUsuario(); // Retorna el nombre de usuario
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ALUMNO"));
        return authorities;
    }


}
