package Proyecto_Prog3.Proyecto_Prog3.Services;
import Proyecto_Prog3.Proyecto_Prog3.models.Pedido;
import Proyecto_Prog3.Proyecto_Prog3.models.TipoEnvio;

import java.util.List;
import java.util.Optional;
public interface PedidoService{
    Optional<Pedido> findPedidoByNumPedido(Long numPedido);
    List<Pedido> findPedidosByClienteId(Long clienteId);
    List<Pedido> findPedidosByEnvioId(Long envioId);
    Pedido createPedido(Pedido pedido);
    Pedido updatePedido(Pedido pedido);
    void deletePedido(Pedido pedido);
    List<Pedido> getAllPedidos();

    void deletePedidoById(Long id);

    Pedido realizarCompra(Long carritoId, Long tipoEnvioId, String direccion);
}
