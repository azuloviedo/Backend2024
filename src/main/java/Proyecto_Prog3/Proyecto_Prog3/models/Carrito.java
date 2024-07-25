package Proyecto_Prog3.Proyecto_Prog3.models;
import java.util.List;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;
import java.util.Set;
import jakarta.persistence.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Entity
public class Carrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCarrito;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ElementCollection
    @CollectionTable(
            name = "carrito_producto",
            joinColumns = @JoinColumn(name = "carrito_id")
    )
    @MapKeyJoinColumn(name = "producto_id")
    @Column(name = "cantidad")
    private Map<Producto, Integer> productos = new HashMap<>();

    public Carrito() {}

    public Carrito(Cliente cliente) {
        this.cliente = cliente;
    }

    // Getters y setters
    public Long getIdCarrito() {
        return idCarrito;
    }

    public void setIdCarrito(Long idCarrito) {
        this.idCarrito = idCarrito;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void agregarProducto(Producto producto, int cantidad) {
        productos.put(producto, cantidad);
    }

    public float calcularTotal() {
        return productos.entrySet().stream()
                .map(entry -> entry.getKey().getPrecio() * entry.getValue())
                .reduce(0.0f, Float::sum);
    }

    public List<Producto> getProductosList() {
        return new ArrayList<>(productos.keySet());
    }

    // Método para obtener la cantidad de un producto
    public Integer getCantidad(Producto producto) {
        return productos.get(producto);
    }

    public Map<Producto, Integer> getProductos() {
        return productos;
    }

    public void setProductos(Map<Producto, Integer> productos) {
        this.productos = productos;
    }

}


/*@Entity
public class Carrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCarrito;

    @OneToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToMany
    @JoinTable(
            name = "carrito_producto",
            joinColumns = @JoinColumn(name = "carrito_id"),
            inverseJoinColumns = @JoinColumn(name = "producto_id")
    )
    private List<Producto> productos;

    @Transient
    private Map<Producto, Integer> cantidades = new HashMap<>();

    public Carrito() {}

    public Carrito(Cliente cliente) {
        this.cliente = cliente;
    }

    // Getters y setters
    public Long getIdCarrito() {
        return idCarrito;
    }

    public void setIdCarrito(Long idCarrito) {
        this.idCarrito = idCarrito;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }


    public void agregarProducto(Producto producto, int cantidad) {
        if (cantidades.containsKey(producto)) {
            cantidades.put(producto, cantidades.get(producto) + cantidad);
        } else {
            productos.add(producto);
            cantidades.put(producto, cantidad);
        }
    }

    public float calcularTotal() {
        return cantidades.entrySet().stream()
                .map(entry -> entry.getKey().getPrecio() * entry.getValue())
                .reduce(0.0f, Float::sum);
    }


    public List<Producto> getProductosList() {
        return new ArrayList<>(cantidades.keySet());
    }

}*/

