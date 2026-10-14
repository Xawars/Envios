package modelos;

public class Terrestre extends Envio {
    public Terrestre(DatosEnvio datos) {
        super(datos);
    }

    public TipoEnvio getTipo() {
        return TipoEnvio.TERRESTRE;
    }

    protected double tarifaBasePorKm() {
        return 1500;
    }

    protected double recargoPorKg() {
        return 2000;
    }
}