package Proyecto_Prog3.Proyecto_Prog3.Services;
import Proyecto_Prog3.Proyecto_Prog3.Repositories.CarritoRepository;
import Proyecto_Prog3.Proyecto_Prog3.Repositories.DetallePedidoRepository;
import Proyecto_Prog3.Proyecto_Prog3.Repositories.PedidoRepository;
import Proyecto_Prog3.Proyecto_Prog3.Repositories.TipoEnvioRepository;
import Proyecto_Prog3.Proyecto_Prog3.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoServiceImpl implements PedidoService{

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private TipoEnvioRepository tipoEnvioRepository;

    @Override
    public Optional<Pedido> findPedidoByNumPedido(Long numPedido) {
        return pedidoRepository.findByNumPedido(numPedido);
    }

    @Override
    public List<Pedido> findPedidosByClienteId(Long clienteId) {
        return pedidoRepository.findByCliente_Id(clienteId);
    }

    @Override
    public List<Pedido> findPedidosByEnvioId(Long envioId) {
        return pedidoRepository.findByEnvio_IdEnvio(envioId);
    }

    @Override
    public Pedido createPedido(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    @Override
    public Pedido updatePedido(Pedido pedido) {
        return this.pedidoRepository.save(pedido);
    }

    @Override
    public void deletePedido(Pedido pedido) {
        pedidoRepository.delete(pedido);
    }

    @Override
    public List<Pedido> getAllPedidos() {
        return (List<Pedido>) pedidoRepository.findAll();
    }

    @Autowired
    private CarritoRepository carritoRepository;

    @Autowired
    private DetallePedidoRepository detallePedidoRepository;

    @Override
    public Pedido realizarCompra(Long carritoId, Long tipoEnvioId, String direccion) {
        // Buscar el carrito por ID
        Carrito carrito = carritoRepository.findById(carritoId)
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado"));

        // Buscar el tipo de envío por ID
        TipoEnvio tipoEnvio = tipoEnvioRepository.findByIdEnvio(tipoEnvioId)
                .orElseThrow(() -> new RuntimeException("Tipo de envío no encontrado"));

        // Crear el nuevo pedido
        Pedido pedido = new Pedido();
        pedido.setFecha(new Date()); // Fecha actual
        pedido.setDireccion(direccion);
        pedido.setEnvio(tipoEnvio);
        pedido.setCliente(carrito.getCliente()); // Asocia al cliente del carrito

        // Guardar el pedido
        Pedido pedidoGuardado = pedidoRepository.save(pedido);

        // Crear los detalles del pedido
        for (Producto producto : carrito.getProductosList()) {
            int cantidad = carrito.getCantidad(producto);
            DetallePedido detalle = new DetallePedido();
            detalle.setPedido(pedidoGuardado);
            detalle.setProducto(producto);
            detalle.setCantidadItem(cantidad);
            detalle.setPrecio(producto.getPrecio());
            detalle.setEstado(false); // Estado inicial del detalle

            // Guardar el detalle del pedido
            detallePedidoRepository.save(detalle);
        }

        // Limpiar el carrito después de la compra
        carrito.getProductosList().clear(); // Vaciar la lista de productos
        carrito.getProductos().clear(); // Vaciar el mapa de productos
        carritoRepository.save(carrito);

        return pedidoGuardado;
    }

    @Override
    public void deletePedidoById(Long id) {
        if (!pedidoRepository.existsById(id)) {
            throw new RuntimeException("Pedido con ID " + id + " no existe");
        }
        pedidoRepository.deleteById(id);
    }
}
