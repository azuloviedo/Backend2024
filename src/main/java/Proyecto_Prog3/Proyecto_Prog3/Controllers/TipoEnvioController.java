package Proyecto_Prog3.Proyecto_Prog3.Controllers;

import Proyecto_Prog3.Proyecto_Prog3.Services.TipoEnvioService;
import Proyecto_Prog3.Proyecto_Prog3.models.TipoEnvio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value= "/api/tipoEnvio")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.PUT, RequestMethod.POST})
public class TipoEnvioController {

    @Autowired
    private TipoEnvioService tipoEnvioService;

    @GetMapping(value = "/id/{idEnvio}")
    public Optional<TipoEnvio> findTipoEnvioById(@PathVariable Long idEnvio) {
        return tipoEnvioService.findTipoEnvioById(idEnvio);
    }

    @GetMapping(value = "/nombre/{nombre}")
    public List<TipoEnvio> findTiposEnvioByNombre(@PathVariable String nombre) {
        return tipoEnvioService.findTiposEnvioByNombre(nombre);
    }

    @PostMapping(value = "")
    public TipoEnvio createTipoEnvio(@RequestBody TipoEnvio tipoEnvio) {
        return tipoEnvioService.createTipoEnvio(tipoEnvio);
    }

    @PutMapping(value = "")
    public TipoEnvio updateTipoEnvio(@RequestBody TipoEnvio tipoEnvio) {
        return tipoEnvioService.updateTipoEnvio(tipoEnvio);
    }

    @DeleteMapping(value = "")
    public void deleteTipoEnvio(@RequestBody TipoEnvio tipoEnvio) {
        tipoEnvioService.deleteTipoEnvio(tipoEnvio);
    }

    @GetMapping
    public List<TipoEnvio> getAllTiposEnvio() {
        return tipoEnvioService.getAllTiposEnvio();
    }

}
