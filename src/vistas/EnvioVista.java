package vistas;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import modelos.TipoEnvio;

public class EnvioVista extends JFrame {
    private final JTextField txtCliente = new JTextField();
    private final JTextField txtCodigo = new JTextField();
    private final JTextField txtPeso = new JTextField();
    private final JTextField txtDistancia = new JTextField();
    private final JComboBox<TipoEnvio> cmbTipo =
            new JComboBox<>(TipoEnvio.values());
    private final JButton btnAgregar = new JButton("Agregar");
    private final JButton btnRetirar = new JButton("Retirar");
    private final JTable tblEnvios = new JTable();

    public EnvioVista() {
        setTitle("Envíos");
        setSize(640, 420);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel formulario = new JPanel(new GridLayout(5, 2, 8, 8));
        formulario.add(new JLabel("Tipo"));
        formulario.add(cmbTipo);
        formulario.add(new JLabel("Cliente"));
        formulario.add(txtCliente);
        formulario.add(new JLabel("Código (también para retirar)"));
        formulario.add(txtCodigo);
        formulario.add(new JLabel("Peso (kg)"));
        formulario.add(txtPeso);
        formulario.add(new JLabel("Distancia (km)"));
        formulario.add(txtDistancia);

        JPanel botones = new JPanel();
        botones.add(btnAgregar);
        botones.add(btnRetirar);

        JPanel norte = new JPanel(new BorderLayout());
        norte.add(formulario, BorderLayout.CENTER);
        norte.add(botones, BorderLayout.SOUTH);

        add(norte, BorderLayout.NORTH);
        add(new JScrollPane(tblEnvios), BorderLayout.CENTER);
    }

    public String getCliente() {
        return txtCliente.getText().trim();
    }

    public String getCodigo() {
        return txtCodigo.getText().trim();
    }

    public String getCodigoRetiro() {
        return getCodigo();
    }

    public double getPeso() {
        return leerDecimal(txtPeso);
    }

    public double getDistancia() {
        return leerDecimal(txtDistancia);
    }

    public TipoEnvio getTipoSeleccionado() {
        return (TipoEnvio) cmbTipo.getSelectedItem();
    }

    public void setAgregarClick(ActionListener escuchador) {
        btnAgregar.addActionListener(escuchador);
    }

    public void setRetirarClick(ActionListener escuchador) {
        btnRetirar.addActionListener(escuchador);
    }

    public void mostrarEnvios(String[][] datos, String[] encabezados) {
        tblEnvios.setModel(new DefaultTableModel(datos, encabezados) {
            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        });
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public void limpiarFormulario() {
        txtCliente.setText("");
        txtCodigo.setText("");
        txtPeso.setText("");
        txtDistancia.setText("");
    }

    private double leerDecimal(JTextField campo) {
        try {
            return Double.parseDouble(campo.getText().trim());
        } catch (NumberFormatException ex) {
            return -1;
        }
    }
}
