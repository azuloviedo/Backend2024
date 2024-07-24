package Proyecto_Prog3.Proyecto_Prog3.Services;
import Proyecto_Prog3.Proyecto_Prog3.models.Carrito;
import Proyecto_Prog3.Proyecto_Prog3.models.Cliente;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface CarritoService {
    Carrito findCarritoByCliente(String DNI);
    Carrito createCarrito(Carrito carrito);
    Carrito updateCarrito(Carrito carrito);
    void deleteCarrito(Carrito carrito);
    List<Carrito> getAllCarritos();
}
