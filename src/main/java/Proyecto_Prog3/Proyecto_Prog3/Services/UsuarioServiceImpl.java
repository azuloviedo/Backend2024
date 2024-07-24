package Proyecto_Prog3.Proyecto_Prog3.Services;
import Proyecto_Prog3.Proyecto_Prog3.Configuration.JwtUtil;
import Proyecto_Prog3.Proyecto_Prog3.Repositories.CarritoRepository;
import Proyecto_Prog3.Proyecto_Prog3.Repositories.PedidoRepository;
import Proyecto_Prog3.Proyecto_Prog3.Repositories.ProductoRepository;
import Proyecto_Prog3.Proyecto_Prog3.Repositories.UsuarioRepository;
import Proyecto_Prog3.Proyecto_Prog3.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.List;


@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CarritoRepository carritoRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public Optional<Usuario> findUsuarioById(Long id) {
        return usuarioRepository.findById(id);
    }

    @Override
    public Optional<Cliente> findClienteById(Long id) {
        return usuarioRepository.findClienteById(id);
    }

   /* @Override
    public Usuario findUsuarioByNombreUsuario(String nombreUsuario) {
        return usuarioRepository.findByNombreUsuario(nombreUsuario);
    }*/

    @Override
    public String authenticate(String username, String password) {
        Usuario user = this.usuarioRepository.findByNombreUsuario(username).orElse(null);
        if (user == null) {
            return null;
        }
        // Generar el token a retornar
        String token = jwtUtil.generateToken(user.getUsername(), user.getId(), user.getRole());
        System.out.println(token);
        return token;

    }

    @Override
    public List<Usuario> findUsuariosByNombreYApellido(String nombre, String apellido) {
        return usuarioRepository.findByNombreAndApellido(nombre, apellido);
    }

    @Override
    public List<Cliente> getAllClientes() {
        return usuarioRepository.findAllClientes();
    }

    @Override
    public List<Cliente> findClientesByDNI(String dni) {
        return usuarioRepository.findClientesByDNI(dni);
    }

    @Override
    public List<Cliente> findClientesByMail(String mail) {
        return usuarioRepository.findClientesByMail(mail);
    }

    @Override
    public boolean existsByNombreUsuario(String nombreUsuario) {
        return usuarioRepository.existsByNombreUsuario(nombreUsuario);
    }

    @Override
    public Administrador createAdministrador(Administrador administrador) {
        if (usuarioRepository.existsByNombreUsuario(administrador.getNombreUsuario())) {
            throw new IllegalArgumentException("El nombre de usuario ya existe");
        }
        return usuarioRepository.save(administrador);
    }

    @Override
    public Cliente createCliente(Cliente cliente) {
        if (existsByNombreUsuario(cliente.getNombreUsuario())) {
            throw new IllegalArgumentException("El nombre de usuario ya existe");
        }

        // Crear un carrito vacío para el cliente
        Carrito carrito = new Carrito();
        carrito.setCliente(cliente);

        cliente.setCarrito(carrito); // Asociar el carrito al cliente

        return usuarioRepository.save(cliente);
    }

    @Override
    public Carrito agregarProductoACarrito(Long clienteId, Long productoId, int cantidad) {
        Cliente cliente = usuarioRepository.findClienteById(clienteId)
                .orElseThrow(() -> new IllegalArgumentException("Cliente con ID " + clienteId + " no encontrado"));

        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new IllegalArgumentException("Producto con ID " + productoId + " no encontrado"));

        Carrito carrito = cliente.getCarrito();
        carrito.agregarProducto(producto, cantidad);

        return carritoRepository.save(carrito);
    }

    public float obtenerTotalCarrito(Long clienteId) {
        Cliente cliente = usuarioRepository.findClienteById(clienteId)
                .orElseThrow(() -> new IllegalArgumentException("Cliente con ID " + clienteId + " no encontrado"));

        return cliente.getCarrito().calcularTotal();
    }

    @Override
    public Usuario updateUsuario(Usuario usuario) {
        if (!usuarioRepository.existsById(usuario.getId())) {
            throw new IllegalArgumentException("Usuario con ID " + usuario.getId() + " no existe");
        }
        return usuarioRepository.save(usuario);
    }

    @Override
    public void deleteUsuario(Usuario usuario) {
        if (!usuarioRepository.existsById(usuario.getId())) {
            throw new IllegalArgumentException("Usuario con ID " + usuario.getId() + " no existe");
        }
        usuarioRepository.delete(usuario);
    }


    @Override
    public List<Usuario> getAllUsuarios() {
        return (List<Usuario>) usuarioRepository.findAll();
    }
}


/*@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CarritoRepository carritoRepository;

    @Autowired
    private ProductoRepository productoRepository;


    @Override
    public Optional<Usuario> findUsuarioById(Long id) {
        return usuarioRepository.findById(id);
    }

    @Override
    public Optional<Cliente> findClienteById(Long id) {
        return usuarioRepository.findClienteById(id);
    }


    @Override
    public Usuario findUsuarioByNombreUsuario(String nombreUsuario) {
        return usuarioRepository.findByNombreUsuario(nombreUsuario);
    }

    @Override
    public List<Usuario> findUsuariosByNombreYApellido(String nombre, String apellido) {
        return usuarioRepository.findByNombreAndApellido(nombre, apellido);
    }

    @Override
    public List<Cliente> getAllClientes() {
        return usuarioRepository.findAllClientes();
    }

    @Override
    public List<Cliente> findClientesByDNI(String dni) {
        return usuarioRepository.findClientesByDNI(dni);
    }

    @Override
    public List<Cliente> findClientesByMail(String mail) {
        return usuarioRepository.findClientesByMail(mail);
    }

    @Override
    public boolean existsByNombreUsuario(String nombreUsuario) {
        return usuarioRepository.existsByNombreUsuario(nombreUsuario);
    }

    @Override
    public Administrador createAdministrador(Administrador administrador) {
        if (usuarioRepository.existsByNombreUsuario(administrador.getNombreUsuario())) {
            throw new IllegalArgumentException("El nombre de usuario ya existe");
        }
        return usuarioRepository.save(administrador);
    }

    @Override
    public Cliente createCliente(Cliente cliente) {
        if (existsByNombreUsuario(cliente.getNombreUsuario())) {
            throw new IllegalArgumentException("El nombre de usuario ya existe");
        }

        // Crear un carrito vacío para el cliente
        Carrito carrito = new Carrito();
        carrito.setCliente(cliente);

        cliente.setCarrito(carrito); // Asociar el carrito al cliente

        return usuarioRepository.save(cliente);
    }

    @Override
    public Carrito agregarProductoACarrito(Long clienteId, Long productoId, int cantidad) {
        Cliente cliente = usuarioRepository.findClienteById(clienteId)
                .orElseThrow(() -> new IllegalArgumentException("Cliente con ID " + clienteId + " no encontrado"));

        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new IllegalArgumentException("Producto con ID " + productoId + " no encontrado"));

        Carrito carrito = cliente.getCarrito();
        carrito.agregarProducto(producto, cantidad);

        return carritoRepository.save(carrito);
    }


    public float obtenerTotalCarrito(Long clienteId) {
        Cliente cliente = usuarioRepository.findClienteById(clienteId)
                .orElseThrow(() -> new IllegalArgumentException("Cliente con ID " + clienteId + " no encontrado"));

        return cliente.getCarrito().calcularTotal();
    }


    @Override
    public Usuario updateUsuario(Usuario usuario) {
        if (!usuarioRepository.existsById(usuario.getId())) {
            throw new IllegalArgumentException("Usuario con ID " + usuario.getId() + " no existe");
        }
        return usuarioRepository.save(usuario);
    }

    @Override
    public void deleteUsuario(Usuario usuario) {
        if (!usuarioRepository.existsById(usuario.getId())) {
            throw new IllegalArgumentException("Usuario con ID " + usuario.getId() + " no existe");
        }
        usuarioRepository.delete(usuario);
    }

    @Override
    public List<Usuario> getAllUsuarios() {
        return (List<Usuario>) usuarioRepository.findAll();
    }
}*/

