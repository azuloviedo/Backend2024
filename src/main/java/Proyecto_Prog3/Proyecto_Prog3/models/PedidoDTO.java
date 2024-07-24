package Proyecto_Prog3.Proyecto_Prog3.models;

public class PedidoDTO {


        private Long carritoId;

        private Long tipoEnvioId;

        private String direccion;

        // Getters y setters

        public Long getCarritoId() {
            return carritoId;
        }

        public void setCarritoId(Long carritoId) {
            this.carritoId = carritoId;
        }

        public Long getTipoEnvioId() {
            return tipoEnvioId;
        }

        public void setTipoEnvioId(Long tipoEnvioId) {
            this.tipoEnvioId = tipoEnvioId;
        }

        public String getDireccion() {
            return direccion;
        }

        public void setDireccion(String direccion) {
            this.direccion = direccion;
        }

}
