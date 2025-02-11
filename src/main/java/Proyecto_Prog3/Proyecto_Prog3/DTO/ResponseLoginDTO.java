package Proyecto_Prog3.Proyecto_Prog3.DTO;

/*public class ResponseLoginDTO {

    private String token;

    public ResponseLoginDTO(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}*/

public class ResponseLoginDTO {
    private String token;
    private String mensaje;

    public ResponseLoginDTO(String token, String mensaje) {
        this.token = token;
        this.mensaje = mensaje;
    }

    // Getters y setters
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}

