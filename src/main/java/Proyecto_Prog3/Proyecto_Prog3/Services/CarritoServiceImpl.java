package Proyecto_Prog3.Proyecto_Prog3.Services;
import Proyecto_Prog3.Proyecto_Prog3.Repositories.CarritoRepository;
import Proyecto_Prog3.Proyecto_Prog3.models.Carrito;
import Proyecto_Prog3.Proyecto_Prog3.models.Cliente;
import Proyecto_Prog3.Proyecto_Prog3.models.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CarritoServiceImpl implements CarritoService {
    @Autowired
    private CarritoRepository carritoRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Override
    public Carrito findCarritoByCliente(String DNI) {
        List<Cliente> cliente = usuarioService.findClientesByDNI(DNI);
        if (cliente.isEmpty()) {
            throw new RuntimeException("Cliente not found with DNI: " + DNI);
        }
        return carritoRepository.findByCliente(cliente.get(0));
    }

    @Override
    public Carrito createCarrito(Carrito carrito) {
        return carritoRepository.save(carrito);
    }

    @Override
    public Carrito updateCarrito(Carrito carrito) {return this.carritoRepository.save(carrito);}

    @Override
    public void deleteCarrito(Carrito carrito) {
        carritoRepository.delete(carrito);
    }

    @Override
    public List<Carrito> getAllCarritos() {
        return (List<Carrito>) carritoRepository.findAll();
    }
}
