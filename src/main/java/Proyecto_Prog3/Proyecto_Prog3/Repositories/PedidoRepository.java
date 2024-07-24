package Proyecto_Prog3.Proyecto_Prog3.Repositories;

import Proyecto_Prog3.Proyecto_Prog3.models.Cliente;
import Proyecto_Prog3.Proyecto_Prog3.models.Pedido;
import Proyecto_Prog3.Proyecto_Prog3.models.TipoEnvio;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PedidoRepository extends CrudRepository<Pedido, Long> {
    Optional<Pedido> findByNumPedido(Long numPedido);
    List<Pedido> findByCliente_Id(Long id);
    List<Pedido> findByEnvio_IdEnvio(Long idEnvio);

}

