package Proyecto_Prog3.Proyecto_Prog3.Controllers;
import Proyecto_Prog3.Proyecto_Prog3.DTO.RegisterDTO;
import Proyecto_Prog3.Proyecto_Prog3.models.Cliente;
import Proyecto_Prog3.Proyecto_Prog3.models.Cliente;
import Proyecto_Prog3.Proyecto_Prog3.models.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import Proyecto_Prog3.Proyecto_Prog3.Services.UsuarioService;
import Proyecto_Prog3.Proyecto_Prog3.DTO.LoginDTO;
import Proyecto_Prog3.Proyecto_Prog3.DTO.ResponseLoginDTO;
import org.springframework.http.*;
import org.springframework.web.server.*;
import java.util.*;


@RestController
@RequestMapping(value = "/api/auth")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class AuthController {
    @Autowired
    private UsuarioService userService;

    /*@PostMapping(path = "/login")
    public ResponseLoginDTO authenticate(@RequestBody LoginDTO loginDTO) {
        return new ResponseLoginDTO(this.userService.authenticate(loginDTO.getUsername(), loginDTO.getPassword()));
    }*/
    /*@PostMapping(path = "/login")
    public ResponseLoginDTO authenticate(@RequestBody LoginDTO loginDTO) {
        String token = this.userService.authenticate(loginDTO.getUsername(), loginDTO.getPassword());
        System.out.println("Token generado: " + token);
        // Decodificar el token para verificar su contenido
        try {
            String[] chunks = token.split("\\.");
            Base64.Decoder decoder = Base64.getUrlDecoder();
            String payload = new String(decoder.decode(chunks[1]));
            System.out.println("Payload del token: " + payload);
        } catch (Exception e) {
            System.out.println("Error decodificando token: " + e.getMessage());
        }
        return new ResponseLoginDTO(token);

    }*/

    @PostMapping(path = "/login")
    public ResponseEntity<?> authenticate(@RequestBody LoginDTO loginDTO) {
        try {
            // Verificar credenciales
            if (!userService.verificarCredenciales(loginDTO.getUsername(), loginDTO.getPassword())) {
                return ResponseEntity
                        .status(HttpStatus.UNAUTHORIZED)
                        .body(new ResponseLoginDTO(null, "Credenciales inválidas"));
            }

            // Si las credenciales son válidas, generamos el token
            String token = userService.authenticate(loginDTO.getUsername(), loginDTO.getPassword());

            System.out.println("Token generado: " + token);
            // Log del payload del token
            try {
                String[] chunks = token.split("\\.");
                Base64.Decoder decoder = Base64.getUrlDecoder();
                String payload = new String(decoder.decode(chunks[1]));
                System.out.println("Payload del token: " + payload);
            } catch (Exception e) {
                System.out.println("Error decodificando token: " + e.getMessage());
            }

            return ResponseEntity.ok(new ResponseLoginDTO(token, "Login exitoso"));

        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(new ResponseLoginDTO(null, "Error en la autenticación: " + e.getMessage()));
        }
    }


    @PostMapping(path = "/register")
    public ResponseEntity<?> register(@RequestBody RegisterDTO registerDTO) {
        try {
            System.out.println("Recibiendo solicitud de registro para usuario: " + registerDTO.getUsername());
            // Crear un nuevo Cliente
            Cliente cliente = new Cliente();
            cliente.setNombreUsuario(registerDTO.getUsername());
            cliente.setContrasenia(registerDTO.getPassword());
            cliente.setMail(registerDTO.getEmail());
            cliente.setNombre(registerDTO.getNombre());
            cliente.setApellido(registerDTO.getApellido());
            cliente.setDireccion(registerDTO.getDireccion());
            cliente.setDNI(registerDTO.getDni());

            System.out.println("Intentando crear cliente con datos: " + cliente);

            // Crear el cliente
            Usuario usuarioCreado = this.userService.createCliente(cliente);

            System.out.println("Usuario creado exitosamente: " + usuarioCreado);

            // Generar y devolver el token
            String token = this.userService.authenticate(
                    registerDTO.getUsername(),
                    registerDTO.getPassword()
            );

            return ResponseEntity.ok(new ResponseLoginDTO(token, "Registro exitoso"));

        } catch (IllegalArgumentException e) {
            System.out.println("Error en el registro: " + e.getMessage());
            e.printStackTrace();

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseLoginDTO(null, "Error en el registro: " + e.getMessage()));
        } catch (Exception e) {
            System.out.println("Error inesperado en el registro: " + e.getMessage());
            e.printStackTrace();

            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseLoginDTO(null, "Error inesperado en el registro"));
        }
    }

   /* @PostMapping(path = "/register")
    public ResponseLoginDTO register(@RequestBody RegisterDTO registerDTO) {
        try {
            System.out.println("Recibiendo solicitud de registro para usuario: " + registerDTO.getUsername());
            // Crear un nuevo Cliente
            Cliente cliente = new Cliente();
            cliente.setNombreUsuario(registerDTO.getUsername());
            cliente.setContrasenia(registerDTO.getPassword());
            cliente.setMail(registerDTO.getEmail());
            cliente.setNombre(registerDTO.getNombre());
            cliente.setApellido(registerDTO.getApellido());
            cliente.setDireccion(registerDTO.getDireccion());  // Nuevo campo
            cliente.setDNI(registerDTO.getDni());              // Nuevo campo


            System.out.println("Intentando crear cliente con datos: " + cliente);

            // Crear el cliente
            Usuario usuarioCreado = this.userService.createCliente(cliente);

            System.out.println("Usuario creado exitosamente: " + usuarioCreado);

            // Generar y devolver el token
            String token = this.userService.authenticate(
                    registerDTO.getUsername(),
                    registerDTO.getPassword()
            );

            return new ResponseLoginDTO(token);
        } catch (IllegalArgumentException e) {

            System.out.println("Error en el registro: " + e.getMessage());
            e.printStackTrace();

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }*/
}


