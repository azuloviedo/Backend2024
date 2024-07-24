package Proyecto_Prog3.Proyecto_Prog3.Controllers;
import Proyecto_Prog3.Proyecto_Prog3.models.PedidoDTO;
import org.springframework.web.bind.annotation.*;
import Proyecto_Prog3.Proyecto_Prog3.Services.PedidoService;
import Proyecto_Prog3.Proyecto_Prog3.models.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value= "/api/pedido")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.PUT, RequestMethod.POST})
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    public ResponseEntity<?> realizarPedido(@RequestBody PedidoDTO pedidoDTO) {
        // Validación manual
        if (pedidoDTO.getCarritoId() == null) {
            return ResponseEntity.badRequest().body("El ID del carrito no puede ser nulo");
        }
        if (pedidoDTO.getTipoEnvioId() == null) {
            return ResponseEntity.badRequest().body("El tipo de envío no puede ser nulo");
        }
        if (pedidoDTO.getDireccion() == null || pedidoDTO.getDireccion().isEmpty()) {
            return ResponseEntity.badRequest().body("La dirección no puede estar vacía");
        }

        // Si la validación es exitosa, proceder con la lógica de negocio
        Pedido pedido = pedidoService.realizarCompra(
                pedidoDTO.getCarritoId(),
                pedidoDTO.getTipoEnvioId(),
                pedidoDTO.getDireccion()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(pedido);
    }

    @GetMapping("numPedido/{numPedido}")
    public ResponseEntity<Pedido> obtenerPedidoPorNumero(@PathVariable Long numPedido) {
        Optional<Pedido> pedido = pedidoService.findPedidoByNumPedido(numPedido);
        if (pedido.isPresent()) {
            return ResponseEntity.ok(pedido.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<Pedido>> obtenerPedidosPorCliente(@PathVariable Long clienteId) {
        List<Pedido> pedidos = pedidoService.findPedidosByClienteId(clienteId);
        if (pedidos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else {
            return ResponseEntity.ok(pedidos);
        }
    }

    @GetMapping("/envio/{envioId}")
    public ResponseEntity<List<Pedido>> obtenerPedidosPorEnvio(@PathVariable Long envioId) {
        List<Pedido> pedidos = pedidoService.findPedidosByEnvioId(envioId);
        if (pedidos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else {
            return ResponseEntity.ok(pedidos);
        }
    }

    @GetMapping
    public ResponseEntity<List<Pedido>> obtenerTodosLosPedidos() {
        List<Pedido> pedidos = pedidoService.getAllPedidos();
        return ResponseEntity.ok(pedidos);
    }

    @PutMapping(value = "")
    public Pedido updatePedido(@RequestBody Pedido pedido) {
        return pedidoService.updatePedido(pedido);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePedido(@PathVariable Long id) {
        try {
            pedidoService.deletePedidoById(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pedido no encontrado");
        }
    }




    /*@GetMapping(value = "/numPedido/{numPedido}")
    public Optional<Pedido> findPedidoByNumPedido(@PathVariable Long numPedido) {
        return pedidoService.findPedidoByNumPedido(numPedido);
    }

   /* @GetMapping(value = "/cliente/{clienteId}")
    public List<Pedido> findPedidosByClienteId(@PathVariable Long clienteId) {
        return pedidoService.findPedidosByClienteId(clienteId);
    }

    @GetMapping(value = "/tipoEnvio/{envioId}")
    public List<Pedido> findPedidosByEnvioId(@PathVariable Long envioId) {
        return pedidoService.findPedidosByEnvioId(envioId);
    }

    @PostMapping(value = "")
    public Pedido createPedido(@RequestBody Pedido pedido) {
        return pedidoService.createPedido(pedido);
    }

    @PutMapping(value = "")
    public Pedido updatePedido(@RequestBody Pedido pedido) {
        return pedidoService.updatePedido(pedido);
    }

    @DeleteMapping(value = "")
    public void deletePedido(@RequestBody Pedido pedido) {
        pedidoService.deletePedido(pedido);
    }

    @GetMapping(value = "")
    public List<Pedido> getAllPedidos() {
        return pedidoService.getAllPedidos();
    }*/
}
