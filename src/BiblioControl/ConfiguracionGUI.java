package BiblioControl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Clase que representa la interfaz gráfica para la configuración por parte del administrador
 */
public class ConfiguracionGUI extends JFrame implements ActionListener{
    private JTextField txtUmbral;
    private JButton btnGuardar, btnCancelar;

    /**
     * Constructor de la clase ConfiguracionGUI
     */
    public ConfiguracionGUI() {
        // Configuración del JFrame
        setTitle("Configuración");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(320, 140);

        // Crear y configurar JPanel con GridLayout
        JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));

        // Etiquetas y campos de texto
        panel.add(new JLabel("Umbral (dB):"));
        txtUmbral = new JTextField();
        panel.add(txtUmbral);

        // Botones
        btnGuardar = new JButton("Guardar");
        btnCancelar = new JButton("Cancelar");

        panel.add(btnGuardar);
        panel.add(btnCancelar);

        btnGuardar.addActionListener(this);
        btnCancelar.addActionListener(this);

        getContentPane().add(panel);
        setVisible(true);
        setLocationRelativeTo(null);
    }

    /**
     * Método que procesa los eventos de la interfaz gráfica
     * @param e el evento
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnGuardar) {
            try {
                // Actualizar umbral convirtiendo el texto a double y llamar al método setUmbralSonido de la clase Admin
                double nuevoUmbral = Double.parseDouble(txtUmbral.getText());
                Admin.getInstance().setUmbralSonido(nuevoUmbral);
                JOptionPane.showMessageDialog(this, "Umbral actualizado a " + nuevoUmbral + " dB");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Por favor, ingresa un número válido");
            }
        } else if (e.getSource() == btnCancelar) {
            dispose();
        }
    }

}
