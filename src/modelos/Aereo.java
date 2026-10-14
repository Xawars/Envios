package modelos;

public class Aereo extends Envio {
    public Aereo(DatosEnvio datos) {
        super(datos);
    }

    public TipoEnvio getTipo() {
        return TipoEnvio.AEREO;
    }

    protected double tarifaBasePorKm() {
        return 5000;
    }

    protected double recargoPorKg() {
        return 4000;
    }
}
