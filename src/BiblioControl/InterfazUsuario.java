package BiblioControl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Clase que representa la interfaz gráfica para el usuario
 */
public class InterfazUsuario extends JFrame implements ActionListener {
    private UsuarioBiblioteca usuarioActual;
    private JButton btnReservarLibro, btnSolicitarLibro, btnDevolverLibro, btnCambiarPassword, btnSalir;

    /**
     * Constructor de la clase InterfazUsuario que recibe un usuario y muestra la interfaz gráfica para el usuario
     * @param usuario el usuario
     */
    public InterfazUsuario(UsuarioBiblioteca usuario) {
        this.usuarioActual = usuario;
        setTitle("Usuario - BiblioControl");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(190, 180);
        setLocationRelativeTo(null);

        btnReservarLibro = new JButton("Reservar Libro");
        btnSolicitarLibro = new JButton("Solicitar Libro");
        btnDevolverLibro = new JButton("Devolver Libro");
        btnCambiarPassword = new JButton("Cambiar Contraseña");
        btnSalir = new JButton("Salir");

        btnReservarLibro.addActionListener(this);
        btnSolicitarLibro.addActionListener(this);
        btnDevolverLibro.addActionListener(this);
        btnCambiarPassword.addActionListener(this);
        btnSalir.addActionListener(this);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        btnReservarLibro.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnSolicitarLibro.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnDevolverLibro.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnCambiarPassword.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnSalir.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(btnReservarLibro);
        panel.add(btnSolicitarLibro);
        panel.add(btnDevolverLibro);
        panel.add(btnCambiarPassword);
        panel.add(btnSalir);

        getContentPane().add(panel);
        setVisible(true);
    }


    /**
     * Método que procesa los eventos de la interfaz gráfica
     * @param e el evento
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnReservarLibro) {
            new ReservarLibroGUI(usuarioActual, Main.getLibros());
        } else if (e.getSource() == btnSolicitarLibro) {
            new SolicitarLibroGUI(usuarioActual, Main.getLibros());
        } else if (e.getSource() == btnDevolverLibro) {
            new DevolverLibroGUI(usuarioActual, Main.getLibros());
        } else if (e.getSource() == btnCambiarPassword) {
            new CambiarPasswordGUI(usuarioActual);
        } else if (e.getSource() == btnSalir) {
            dispose(); // Cierra la ventana
            new InterfazBiblioControl(); // Abre la interfaz de inicio de sesión
        }
    }
}
