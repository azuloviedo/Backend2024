package Proyecto_Prog3.Proyecto_Prog3.Services;

import Proyecto_Prog3.Proyecto_Prog3.models.DetallePedido;

import java.util.List;
import java.util.Optional;

public interface DetallePedidoService {
    Optional<DetallePedido> findDetallePedidoById(Long id);
    List<DetallePedido> findDetallesPedidoByEstado(boolean estado);
    List<DetallePedido> findDetallesPedidoByNumPedido(Long numPedido);
    DetallePedido createDetallePedido(DetallePedido detallePedido);
    DetallePedido updateDetallePedido(DetallePedido detallePedido);
    void deleteDetallePedido(DetallePedido detallePedido);
    List<DetallePedido> getAllDetallesPedido();

    DetallePedido updateEstado(Long id, boolean estado);
}
