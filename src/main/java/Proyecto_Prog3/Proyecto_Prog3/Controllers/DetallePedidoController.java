package Proyecto_Prog3.Proyecto_Prog3.Controllers;
import Proyecto_Prog3.Proyecto_Prog3.Services.DetallePedidoService;
import Proyecto_Prog3.Proyecto_Prog3.models.DetallePedido;
import Proyecto_Prog3.Proyecto_Prog3.models.UpdateEstadoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping(value= "/api/detallePedido")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.PUT, RequestMethod.POST})
public class DetallePedidoController {
    @Autowired
    private DetallePedidoService detallePedidoService;

    @GetMapping(value = "/id/{id}")
    public Optional<DetallePedido> findDetallePedidoById(@PathVariable Long id) {
        return detallePedidoService.findDetallePedidoById(id);
    }

    @GetMapping(value = "/estado/{estado}")
    public List<DetallePedido> findDetallesPedidoByEstado(@PathVariable boolean estado) {
        return detallePedidoService.findDetallesPedidoByEstado(estado);
    }

    @GetMapping(value = "/pedido/{numPedido}")
    public List<DetallePedido> findDetallesPedidoByNumPedido(@PathVariable Long numPedido) {
        return detallePedidoService.findDetallesPedidoByNumPedido(numPedido);
    }

    @PostMapping(value = "")
    public DetallePedido createDetallePedido(@RequestBody DetallePedido detallePedido) {
        return detallePedidoService.createDetallePedido(detallePedido);
    }

    @PutMapping(value = "")
    public DetallePedido updateDetallePedido(@RequestBody DetallePedido detallePedido) {
        return detallePedidoService.updateDetallePedido(detallePedido);
    }

    @PutMapping(value = "/estado")
    public ResponseEntity<DetallePedido> updateEstado(@RequestBody UpdateEstadoRequest request) {
        try {
            DetallePedido updatedDetallePedido = detallePedidoService.updateEstado(request.getId(), request.isEstado());
            return ResponseEntity.ok(updatedDetallePedido);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping(value = "")
    public void deleteDetallePedido(@RequestBody DetallePedido detallePedido) {
        detallePedidoService.deleteDetallePedido(detallePedido);
    }

    @GetMapping( value = "")
    public List<DetallePedido> getAllDetallesPedido() {
        return detallePedidoService.getAllDetallesPedido();
    }
}
