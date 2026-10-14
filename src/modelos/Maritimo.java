package modelos;

public class Maritimo extends Envio {
    public Maritimo(DatosEnvio datos) {
        super(datos);
    }

    public TipoEnvio getTipo() {
        return TipoEnvio.MARITIMO;
    }

    protected double tarifaBasePorKm() {
        return 800;
    }

    protected double recargoPorKg() {
        return 1000;
    }
}
