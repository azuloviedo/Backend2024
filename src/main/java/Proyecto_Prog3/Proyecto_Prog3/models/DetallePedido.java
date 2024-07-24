package Proyecto_Prog3.Proyecto_Prog3.models;
import jakarta.persistence.*;


@Entity
public class DetallePedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;


    private int cantidadItem;
    private boolean estado;
    private float precio;

    public DetallePedido(Long id, Pedido pedido, Producto producto, int cantidadItem, boolean estado, float precio) {
        this.id = id;
        this.pedido = pedido;
        this.producto = producto;
        this.cantidadItem = cantidadItem;
        this.estado = estado;
        this.precio = precio;
    }

    public DetallePedido()
    {

    }

    // Getters y Setters

    public Long getId() {
        return id;
    }

    /*public void setPedido(Long id) {
        this.id = id;
    }*/

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidadItem() {
        return cantidadItem;
    }

    public void setCantidadItem(int cantidadItem) {
        this.cantidadItem = cantidadItem;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }
}

