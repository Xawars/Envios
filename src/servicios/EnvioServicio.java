package servicios;

import java.util.ArrayList;
import java.util.List;

import fabricas.EnvioFactory;
import modelos.DatosEnvio;
import modelos.Envio;
import modelos.TipoEnvio;

public class EnvioServicio {
    private static final String[] encabezados = {
            "Código", "Cliente", "Tipo", "Peso", "Distancia", "Tarifa"
    };

    private static final List<Envio> envios = new ArrayList<>();

    public static String[] getEncabezados() {
        return encabezados;
    }

    public static Envio agregar(TipoEnvio tipo, DatosEnvio datos) {
        if (indiceDe(datos.codigo()) >= 0) {
            return null;
        }
        var envio = EnvioFactory.getFactory(tipo).crearEnvio(datos);
        envios.add(envio);
        return envio;
    }

    public static boolean retirar(String codigo) {
        int indice = indiceDe(codigo);
        if (indice < 0) {
            return false;
        }
        envios.remove(indice);
        return true;
    }

    public static String[][] getDatos() {
        String[][] datos = new String[envios.size()][encabezados.length];
        int fila = 0;
        for (var envio : envios) {
            int columna = 0;
            for (var dato : envio.getDatos()) {
                if (columna < encabezados.length) {
                    datos[fila][columna] = dato;
                }
                columna++;
            }
            fila++;
        }
        return datos;
    }

    private static int indiceDe(String codigo) {
        for (int i = 0; i < envios.size(); i++) {
            if (envios.get(i).getCodigo().equals(codigo)) {
                return i;
            }
        }
        return -1;
    }
}
