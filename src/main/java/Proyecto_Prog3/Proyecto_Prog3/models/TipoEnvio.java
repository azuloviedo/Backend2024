package Proyecto_Prog3.Proyecto_Prog3.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class TipoEnvio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEnvio;
    private String nombre;
    private float costo;
    private String detalle;

    @OneToMany(mappedBy = "envio")
    private List<Pedido> pedidos;

    // Constructor
    public TipoEnvio(Long idEnvio, String nombre, float costo, String detalle) {
        this.idEnvio = idEnvio;
        this.nombre = nombre;
        this.costo = costo;
        this.detalle = detalle;
    }

    public TipoEnvio()
    {

    }

    // Getters y Setters
    public Long getIdEnvio() {
        return idEnvio;
    }

    public void setIdEnvio(Long idEnvio) {
        this.idEnvio = idEnvio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getCosto() {
        return costo;
    }

    public void setCosto(float costo) {
        this.costo = costo;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }
}

