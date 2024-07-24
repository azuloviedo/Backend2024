package Proyecto_Prog3.Proyecto_Prog3.models;

public class UpdateEstadoRequest {
    private Long id;
    private boolean estado;

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}
