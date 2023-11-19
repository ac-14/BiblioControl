package BiblioControl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Clase que representa la interfaz gráfica para iniciar sesión
 */
public class InterfazBiblioControl extends JFrame implements ActionListener {
    private JTextField txtDNI;
    private JPasswordField txtPassword;
    private JButton btnIniciarSesion;

    /**
     * Constructor de la clase InterfazBiblioControl que muestra la interfaz gráfica para iniciar sesión
     */
    public InterfazBiblioControl() {
        setTitle("BiblioControl - Iniciar Sesión");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(320, 150);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Panel para los campos de texto
        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        txtDNI = new JTextField();
        txtPassword = new JPasswordField();

        inputPanel.add(new JLabel("DNI:"));
        inputPanel.add(txtDNI);
        inputPanel.add(new JLabel("Contraseña:"));
        inputPanel.add(txtPassword);

        // Panel para el botón
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnIniciarSesion = new JButton("Iniciar Sesión");
        btnIniciarSesion.addActionListener(this);
        buttonPanel.add(btnIniciarSesion);

        // Añadiendo paneles al JFrame
        add(inputPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    /**
     * Método que procesa los eventos de la interfaz gráfica
     * @param e el evento
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnIniciarSesion) {
            String dniIngresado = txtDNI.getText();
            String passwordIngresado = new String(txtPassword.getPassword());

            // Lógica para verificar el inicio de sesión
            if (Admin.getInstance().autenticarAdmin(passwordIngresado)) {
                // Inicio de sesión como administrador
                Admin.getInstance().Menu(Main.getUsuarios(), Main.getLibros());
                dispose();
            } else {
                UsuarioBiblioteca usuario = UsuarioBiblioteca.buscarUsuarioPorDNI(dniIngresado, Main.getUsuarios());
                if (usuario != null) {
                    if (usuario.ComprobarPassword(passwordIngresado)) {
                        // Inicio de sesión como usuario de la biblioteca
                        usuario.Menu(Main.getUsuarios(), Main.getLibros());
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(this, "Contraseña incorrecta. Pista: " + usuario.getPistaPassword(), "Error de Inicio de Sesión", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "DNI o contraseña incorrectos.", "Error de Inicio de Sesión", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }


}