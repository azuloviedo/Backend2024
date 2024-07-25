package Proyecto_Prog3.Proyecto_Prog3.models;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Date;
import java.util.List;

@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numPedido;
    private Date fecha;
    private String direccion;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "idEnvio")
    private TipoEnvio envio;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @OneToMany(mappedBy = "pedido")
    @JsonIgnore
    private List<DetallePedido> detallesPedido;

    public Pedido(Long numPedido, Date fecha, String direccion, TipoEnvio envio, Cliente cliente, List<DetallePedido> detallesPedido) {
        this.numPedido = numPedido;
        this.fecha = fecha;
        this.direccion = direccion;
        this.envio = envio;
        this.cliente = cliente;
        this.detallesPedido = detallesPedido;
    }

    public Pedido(){


    }

    // Getters y Setters
    public Long getNumPedido() {
        return numPedido;
    }

    public void setNumPedido(Long numPedido) {
        this.numPedido = numPedido;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public TipoEnvio getEnvio() {
        return envio;
    }

    public void setEnvio(TipoEnvio envio) {
        this.envio = envio;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
