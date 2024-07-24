package Proyecto_Prog3.Proyecto_Prog3.Services;
import java.util.List;
import Proyecto_Prog3.Proyecto_Prog3.models.Usuario;
import Proyecto_Prog3.Proyecto_Prog3.models.Cliente;
import java.util.Optional;
import Proyecto_Prog3.Proyecto_Prog3.models.*;
import java.util.List;


public interface UsuarioService {
    Optional<Usuario> findUsuarioById(Long id);

    boolean existsByNombreUsuario(String nombreUsuario);
    //Usuario findUsuarioByNombreUsuario(String nombreUsuario);
    List<Usuario> findUsuariosByNombreYApellido(String nombre, String apellido);
    List<Cliente> getAllClientes();
    List<Cliente> findClientesByDNI(String dni);
    List<Cliente> findClientesByMail(String mail);

    Optional<Cliente> findClienteById(Long id);

    Usuario createAdministrador(Administrador administrador);
    Usuario createCliente(Cliente cliente);
    Usuario updateUsuario(Usuario usuario);
    void deleteUsuario(Usuario usuario);

    String authenticate(String username, String password);


    List<Usuario> getAllUsuarios();

    Carrito agregarProductoACarrito(Long clienteId, Long productoId, int cantidad);
    float obtenerTotalCarrito(Long clienteId);

}
