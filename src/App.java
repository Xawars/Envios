import controladores.EnvioControlador;
import vistas.EnvioVista;

public class App {
    public static void main(String[] args) {
        var vista = new EnvioVista();
        new EnvioControlador(vista);
        vista.setVisible(true);
    }
}
