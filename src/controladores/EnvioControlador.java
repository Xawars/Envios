package controladores;

import modelos.DatosEnvio;
import servicios.EnvioServicio;
import vistas.EnvioVista;

public class EnvioControlador {
    private final EnvioVista vista;

    public EnvioControlador(EnvioVista vista) {
        this.vista = vista;
        this.vista.setAgregarClick(evento -> agregarEnvio());
        this.vista.setRetirarClick(evento -> retirarEnvio());
        mostrarEnvios();
    }

    private void agregarEnvio() {
        var tipo = vista.getTipoSeleccionado();
        var cliente = vista.getCliente();
        var codigo = vista.getCodigo();
        var peso = vista.getPeso();
        var distancia = vista.getDistancia();

        if (cliente.isEmpty() || codigo.isEmpty()
                || peso <= 0 || distancia <= 0) {
            vista.mostrarMensaje(
                    "El cliente y el código son obligatorios. "
                            + "El peso y la distancia deben ser mayores que 0.");
            return;
        }

        var agregado = EnvioServicio.agregar(
                tipo, new DatosEnvio(cliente, codigo, peso, distancia));
        if (agregado == null) {
            vista.mostrarMensaje("Ya existe un envío con ese código.");
            return;
        }
        mostrarEnvios();
        vista.limpiarFormulario();
    }

    private void retirarEnvio() {
        var codigo = vista.getCodigoRetiro();
        if (codigo.isEmpty()) {
            vista.mostrarMensaje("Escriba el código del envío.");
            return;
        }
        if (!EnvioServicio.retirar(codigo)) {
            vista.mostrarMensaje("No existe un envío con ese código.");
            return;
        }
        mostrarEnvios();
    }

    private void mostrarEnvios() {
        vista.mostrarEnvios(
                EnvioServicio.getDatos(), EnvioServicio.getEncabezados());
    }
}
