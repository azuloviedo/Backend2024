package Proyecto_Prog3.Proyecto_Prog3.Controllers;
import Proyecto_Prog3.Proyecto_Prog3.Services.CarritoService;
import Proyecto_Prog3.Proyecto_Prog3.Services.UsuarioService;
import Proyecto_Prog3.Proyecto_Prog3.Services.UsuarioServiceImpl;
import Proyecto_Prog3.Proyecto_Prog3.models.Carrito;
import Proyecto_Prog3.Proyecto_Prog3.models.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping(value= "/api/carrito")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.PUT, RequestMethod.POST})
public class CarritoController {
    @Autowired
    private CarritoService carritoService;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/{clienteId}/producto/{productoId}")
    public ResponseEntity<Carrito> agregarProductoAlCarrito(@PathVariable Long clienteId,
                                                            @PathVariable Long productoId,
                                                            @RequestParam int cantidad) {
        Carrito carrito = usuarioService.agregarProductoACarrito(clienteId, productoId, cantidad);
        return ResponseEntity.ok(carrito);
    }

    @GetMapping("/{clienteId}/total")
    public ResponseEntity<Float> obtenerTotalCarrito(@PathVariable Long clienteId) {
        float total = usuarioService.obtenerTotalCarrito(clienteId);
        return ResponseEntity.ok(total);
    }

    @GetMapping(value = "/cliente/dni/{dni}")
    public Carrito findCarritoByDni(@PathVariable String dni) {
        return carritoService.findCarritoByCliente(dni);
    }

    @PostMapping(value = "")
    public Carrito createCarrito(@RequestBody Carrito carrito) {
        return carritoService.createCarrito(carrito);
    }

    @PutMapping(value= "")
    public Carrito updateCarrito(@RequestBody Carrito carrito) {
        return carritoService.updateCarrito(carrito);
    }

    @DeleteMapping(value = "")
    public void deleteCarrito(@RequestBody Carrito carrito) {
        carritoService.deleteCarrito(carrito);
    }

    @GetMapping(value = "")
    public List<Carrito> getAllCarritos() {
        return carritoService.getAllCarritos();
    }
}
