package Proyecto_Prog3.Proyecto_Prog3.models;
import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.*;
import java.util.ArrayList;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Entity
public class Cliente extends Usuario {
    private String DNI;
    private String mail;
    private String direccion;

    @OneToOne(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private Carrito carrito;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pedido> pedidos = new ArrayList<>();

    public Cliente(Long id, String nombreUsuario, String contraseña, String nombre, String apellido, String DNI, String mail, String direccion) {
        super(id, nombreUsuario, contraseña, nombre, apellido);
        this.DNI = DNI;
        this.mail = mail;
        this.direccion = direccion;
    }

    public Cliente()
    {

    }


    @Override
    public String getRole() {
        return "Cliente";
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
        authorities.add(new SimpleGrantedAuthority("Cliente"));
        return authorities;
    }

    // Getters y Setters

    public Carrito getCarrito() {
        return carrito;
    }

    public void setCarrito(Carrito carrito) {
        this.carrito = carrito;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
