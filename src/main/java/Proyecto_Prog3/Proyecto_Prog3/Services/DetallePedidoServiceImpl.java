package Proyecto_Prog3.Proyecto_Prog3.Services;
import Proyecto_Prog3.Proyecto_Prog3.models.DetallePedido;
import org.springframework.beans.factory.annotation.Autowired;
import Proyecto_Prog3.Proyecto_Prog3.Repositories.DetallePedidoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class DetallePedidoServiceImpl implements DetallePedidoService{
    @Autowired
    private DetallePedidoRepository detallePedidoRepository;

    @Override
    public Optional<DetallePedido> findDetallePedidoById(Long id) {
        return detallePedidoRepository.findById(id);
    }

    @Override
    public List<DetallePedido> findDetallesPedidoByEstado(boolean estado) {
        return detallePedidoRepository.findByEstado(estado);
    }

    @Override
    public List<DetallePedido> findDetallesPedidoByNumPedido(Long numPedido) {
        return detallePedidoRepository.findByPedido_NumPedido(numPedido);
    }

    @Override
    public DetallePedido createDetallePedido(DetallePedido detallePedido) {
        return detallePedidoRepository.save(detallePedido);
    }

    @Override
    public DetallePedido updateDetallePedido(DetallePedido detallePedido) {
            return detallePedidoRepository.save(detallePedido);
    }

    @Override
    public void deleteDetallePedido(DetallePedido detallePedido) {
        detallePedidoRepository.delete(detallePedido);
    }

    @Override
    public List<DetallePedido> getAllDetallesPedido() {
        return (List<DetallePedido>) detallePedidoRepository.findAll();
    }

   @Override
    public DetallePedido updateEstado(Long id, boolean estado) {
        DetallePedido detallePedido = detallePedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("DetallePedido con ID " + id + " no encontrado"));

        detallePedido.setEstado(estado);
        return detallePedidoRepository.save(detallePedido);
    }
}
