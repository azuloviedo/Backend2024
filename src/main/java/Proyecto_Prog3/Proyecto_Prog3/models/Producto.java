package Proyecto_Prog3.Proyecto_Prog3.models;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

@Entity
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProducto;
    private String nombreProducto;
    private String imagen;
    private int stock;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    private float precio;

    @OneToMany(mappedBy = "producto")
    private List<DetallePedido> detallesPedido;

    /*@ManyToMany(mappedBy = "productos")
    private List<Carrito> carritos;*/

    public Producto(Long idProducto, String nombreProducto, String imagen, int stock, Categoria categoria, float precio) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.imagen = imagen;
        this.stock = stock;
        this.categoria = categoria;
        this.precio = precio;
    }

    public Producto() {}

    // Getters y Setters
    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public List<DetallePedido> getDetallesPedido() {
        return detallesPedido;
    }

    public void setDetallesPedido(List<DetallePedido> detallesPedido) {
        this.detallesPedido = detallesPedido;
    }



   /* public List<Carrito> getCarritos() {
        return carritos;
    }

    public void setCarritos(List<Carrito> carritos) {
        this.carritos = carritos;
    }*/
}


/*
@Entity
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProducto;
    private String nombreProducto;
    private String imagen;
    private int stock;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    private float precio;

    @OneToMany(mappedBy = "producto")
    private List<DetallePedido> detallesPedido;

    @OneToMany(mappedBy = "producto")
    private List<CarritoProducto> carritoProductos;

    public Producto(Long idProducto, String nombreProducto, String imagen, int stock, Categoria categoria, float precio) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.imagen = imagen;
        this.stock = stock;
        this.categoria = categoria;
        this.precio = precio;
    }

    public Producto()
    {

    }

    // Getters y Setters
    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }
}*/

