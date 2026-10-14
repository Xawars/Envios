package modelos;

import java.text.DecimalFormat;

public abstract class Envio {
    private final String cliente;
    private final String codigo;
    private final double peso;
    private final double distancia;

    public Envio(DatosEnvio datos) {
        this.cliente = datos.cliente();
        this.codigo = datos.codigo();
        this.peso = datos.peso();
        this.distancia = datos.distancia();
    }

    public String getCliente() {
        return cliente;
    }

    public String getCodigo() {
        return codigo;
    }

    public double getPeso() {
        return peso;
    }

    public double getDistancia() {
        return distancia;
    }

    public abstract TipoEnvio getTipo();

    protected abstract double tarifaBasePorKm();

    protected abstract double recargoPorKg();

    public double calcularTarifa() {
        return tarifaBasePorKm() * getDistancia()
                + recargoPorKg() * getPeso();
    }

    public String[] getDatos() {
        var formato = new DecimalFormat("#,##0.00");
        return new String[] {
                getCodigo(),
                getCliente(),
                getTipo().toString(),
                formato.format(getPeso()),
                formato.format(getDistancia()),
                formato.format(calcularTarifa())
        };
    }
}
