package Proyecto_Prog3.Proyecto_Prog3.Repositories;

import Proyecto_Prog3.Proyecto_Prog3.models.DetallePedido;
import Proyecto_Prog3.Proyecto_Prog3.models.Pedido;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface DetallePedidoRepository extends CrudRepository<DetallePedido, Long> {

    List<DetallePedido> findByEstado(boolean estado);
    List<DetallePedido> findByPedido_NumPedido(Long numPedido);

}
