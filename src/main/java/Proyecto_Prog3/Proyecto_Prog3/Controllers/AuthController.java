package Proyecto_Prog3.Proyecto_Prog3.Controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import Proyecto_Prog3.Proyecto_Prog3.Services.UsuarioService;
import Proyecto_Prog3.Proyecto_Prog3.DTO.LoginDTO;
import Proyecto_Prog3.Proyecto_Prog3.DTO.ResponseLoginDTO;

@RestController
@RequestMapping(value = "/api/auth")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class AuthController {
    @Autowired
    private UsuarioService userService;

    @PostMapping(path = "/login")
    public ResponseLoginDTO authenticate(@RequestBody LoginDTO loginDTO) {
        return new ResponseLoginDTO(this.userService.authenticate(loginDTO.getUsername(), loginDTO.getPassword()));
    }
}
