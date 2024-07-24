package Proyecto_Prog3.Proyecto_Prog3.Repositories;

import Proyecto_Prog3.Proyecto_Prog3.models.Carrito;
import Proyecto_Prog3.Proyecto_Prog3.models.Cliente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarritoRepository extends CrudRepository<Carrito, Long> {

    Carrito findByCliente(Cliente cliente);
}
