package Proyecto_Prog3.Proyecto_Prog3.Controllers;
import Proyecto_Prog3.Proyecto_Prog3.Services.UsuarioService;
import Proyecto_Prog3.Proyecto_Prog3.models.Administrador;
import Proyecto_Prog3.Proyecto_Prog3.models.Usuario;
import Proyecto_Prog3.Proyecto_Prog3.models.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.ResponseEntity;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/usuario")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class UsuarioController {

        @Autowired
        private UsuarioService usuarioService;

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleIllegalArgumentException(IllegalArgumentException ex) {
        return ex.getMessage();
    }

       @GetMapping(value = "id/{id}")
        public Optional<Usuario> findUsuarioById(@PathVariable Long id) {
            return usuarioService.findUsuarioById(id);
        }

        /*@GetMapping("/nombre/{nombreUsuario}")
        public Usuario findUsuarioByNombreUsuario(@PathVariable String nombreUsuario) {
            return usuarioService.findByNombreUsuario(nombreUsuario);
        }*/



        // GET /usuario/buscar?nombre={..}&apellido={...}
    @GetMapping("/search")
    public List<Usuario> findUsuarioByNombreYApellido(@RequestParam String nombre, @RequestParam String apellido) {
        return usuarioService.findUsuariosByNombreYApellido(nombre, apellido);
    }


        @GetMapping(value= "/cliente")
        public List<Cliente> getAllClientes() {
            return usuarioService.getAllClientes();
        }

        @GetMapping(value="/cliente/dni/{dni}")
        public List<Cliente> findClientesByDNI(@PathVariable String dni) {
            return usuarioService.findClientesByDNI(dni);
        }

        @GetMapping(value="/cliente/mail/{mail}")
        public List<Cliente> findClientesByMail(@PathVariable String mail) {
            return usuarioService.findClientesByMail(mail);
        }

        @PostMapping( value = "/cliente")
        public Usuario createCliente(@RequestBody Cliente usuario) {
            return usuarioService.createCliente(usuario);
        }

    @PostMapping(value = "/administrador")
    public Usuario createAdministrador(@RequestBody Administrador usuario) {
        return usuarioService.createAdministrador(usuario);
    }

    @PutMapping("/cliente/{id}")
    public ResponseEntity<String> updateCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
        Optional<Usuario> existingUsuario = usuarioService.findUsuarioById(id);
        if (existingUsuario.isPresent() && existingUsuario.get() instanceof Cliente) {
            Cliente existingCliente = (Cliente) existingUsuario.get();
            existingCliente.setNombreUsuario(cliente.getNombreUsuario());
            existingCliente.setContrasenia(cliente.getContrasenia());
            existingCliente.setNombre(cliente.getNombre());
            existingCliente.setApellido(cliente.getApellido());
            existingCliente.setMail(cliente.getMail());
            existingCliente.setDNI(cliente.getDNI());
            existingCliente.setDireccion(cliente.getDireccion());

            try {
                usuarioService.updateUsuario(existingCliente);
                return new ResponseEntity<>("Cliente actualizado con éxito", HttpStatus.OK);
            } catch (IllegalArgumentException e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>("Cliente con ID " + id + " no encontrado", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/administrador/{id}")
    public ResponseEntity<String> updateAdministrador(@PathVariable Long id, @RequestBody Administrador administrador) {
        Optional<Usuario> existingUsuario = usuarioService.findUsuarioById(id);
        if (existingUsuario.isPresent() && existingUsuario.get() instanceof Administrador) {
            Administrador existingAdministrador = (Administrador) existingUsuario.get();
            existingAdministrador.setNombreUsuario(administrador.getNombreUsuario());
            existingAdministrador.setNombre(administrador.getNombre());
            existingAdministrador.setApellido(administrador.getApellido());
            existingAdministrador.setContrasenia(administrador.getContrasenia());

            try {
                usuarioService.updateUsuario(existingAdministrador);
                return new ResponseEntity<>("Administrador actualizado con éxito", HttpStatus.OK);
            } catch (IllegalArgumentException e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>("Administrador con ID " + id + " no encontrado", HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUsuario(@PathVariable Long id) {
        Optional<Usuario> usuario = usuarioService.findUsuarioById(id);
        if (usuario.isPresent()) {
            usuarioService.deleteUsuario(usuario.get());
            return new ResponseEntity<>("Usuario eliminado con éxito", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Usuario no encontrado", HttpStatus.NOT_FOUND);
        }
    }



    @GetMapping(value= "")
        public List<Usuario> getAllUsuarios() {
            return usuarioService.getAllUsuarios();
        }
}

