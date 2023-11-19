package BiblioControl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Clase que representa la interfaz gráfica para cambiar la contraseña
 */
public class CambiarPasswordGUI extends JFrame implements ActionListener {
    private JPasswordField txtPasswordActual, txtPasswordNueva;
    private JButton btnCambiar, btnCancelar;
    private UsuarioBiblioteca usuarioActual;

    /**
     * Constructor de la clase CambiarPasswordGUI que recibe un usuario y muestra la interfaz gráfica para cambiar la contraseña
     * @param usuario Usuario de la biblioteca
     */
    public CambiarPasswordGUI(UsuarioBiblioteca usuario) {
        this.usuarioActual = usuario;

        // Configuración del JFrame
        setTitle("Cambiar Contraseña");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panel para los campos de contraseña
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        txtPasswordActual = new JPasswordField();
        txtPasswordNueva = new JPasswordField();

        // Añadir botones
        btnCambiar = new JButton("Cambiar");
        btnCancelar = new JButton("Cancelar");
        btnCambiar.addActionListener(this);
        btnCancelar.addActionListener(this);

        panel.add(new JLabel("Contraseña actual:"));
        panel.add(txtPasswordActual);
        panel.add(new JLabel("Nueva contraseña:"));
        panel.add(txtPasswordNueva);
        panel.add(btnCambiar);
        panel.add(btnCancelar);

        // Añadir paneles al JFrame
        add(panel);

        setVisible(true);
        setLocationRelativeTo(null);
    }

    /**
     * Método que procesa los eventos de la interfaz gráfica
     * @param e el evento
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCambiar) {
            try {
                String passwordActual = new String(txtPasswordActual.getPassword());
                String passwordNueva = new String(txtPasswordNueva.getPassword());
                usuarioActual.cambiarPassword(passwordActual, passwordNueva);
                JOptionPane.showMessageDialog(this, "Contraseña cambiada correctamente");
            } catch (UsuarioBiblioteca.PasswordIncorrectaException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        }
        else if (e.getSource() == btnCancelar) {
            dispose(); // Cierra la ventana
        }
    }

}