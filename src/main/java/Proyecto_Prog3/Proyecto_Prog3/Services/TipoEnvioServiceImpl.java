package Proyecto_Prog3.Proyecto_Prog3.Services;
import Proyecto_Prog3.Proyecto_Prog3.Repositories.TipoEnvioRepository;
import Proyecto_Prog3.Proyecto_Prog3.models.TipoEnvio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoEnvioServiceImpl implements  TipoEnvioService{
    @Autowired
    private TipoEnvioRepository tipoEnvioRepository;

    @Override
    public Optional<TipoEnvio> findTipoEnvioById(Long idEnvio) {
        return tipoEnvioRepository.findByIdEnvio(idEnvio);
    }

    @Override
    public List<TipoEnvio> findTiposEnvioByNombre(String nombre) {
        return tipoEnvioRepository.findByNombre(nombre);
    }

    @Override
    public TipoEnvio createTipoEnvio(TipoEnvio tipoEnvio) {
        return tipoEnvioRepository.save(tipoEnvio);
    }

    @Override
    public TipoEnvio updateTipoEnvio(TipoEnvio tipoEnvio) {
        return tipoEnvioRepository.save(tipoEnvio);

    }

    @Override
    public void deleteTipoEnvio(TipoEnvio tipoEnvio) {
        tipoEnvioRepository.delete(tipoEnvio);
    }

    @Override
    public List<TipoEnvio> getAllTiposEnvio() {
        return (List<TipoEnvio>) tipoEnvioRepository.findAll();
    }
}
