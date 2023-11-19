package BiblioControl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Clase que representa la interfaz gráfica para la administración
 */
public class InterfazAdmin extends JFrame implements ActionListener {
    private JButton btnAddUsuario, btnDelUsuario, btnAddLibro, btnDelLibro, btnGestionarSonido, btnGestionarPeticiones, btnSalir;

    /**
     * Constructor de la clase InterfazAdmin que muestra la interfaz gráfica para la administración
     */
    public InterfazAdmin() {
        setTitle("Administración - BiblioControl");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(320, 240);

        // Inicialización y configuración de los botones
        btnAddUsuario = new JButton("Añadir usuario");
        btnDelUsuario = new JButton("Eliminar usuario");
        btnAddLibro = new JButton("Añadir libro");
        btnDelLibro = new JButton("Eliminar libro");
        btnGestionarSonido = new JButton("Gestionar sonido");
        btnGestionarPeticiones = new JButton("Gestionar peticiones");
        btnSalir = new JButton("Salir");

        btnAddUsuario.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnDelUsuario.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnAddLibro.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnDelLibro.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnGestionarSonido.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnGestionarPeticiones.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnSalir.setAlignmentX(Component.CENTER_ALIGNMENT);

        btnAddUsuario.addActionListener(this);
        btnDelUsuario.addActionListener(this);
        btnAddLibro.addActionListener(this);
        btnDelLibro.addActionListener(this);
        btnGestionarSonido.addActionListener(this);
        btnGestionarPeticiones.addActionListener(this);
        btnSalir.addActionListener(this);

        // Creación del panel y añadir los botones a él
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(btnAddUsuario);
        panel.add(btnDelUsuario);
        panel.add(btnAddLibro);
        panel.add(btnDelLibro);
        panel.add(btnGestionarSonido);
        panel.add(btnGestionarPeticiones);
        panel.add(btnSalir);

        // Añadir el panel al JFrame
        getContentPane().add(panel);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Método que procesa los eventos de la interfaz gráfica
     * @param e el evento
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAddUsuario) {
            new AddUsuarioGUI(Main.getUsuarios()); // Abre la ventana para añadir un usuario
        } else if (e.getSource() == btnDelUsuario) {
            new EliminarUsuarioGUI(Main.getUsuarios()); // Abre la ventana para eliminar un usuario
        } else if (e.getSource() == btnAddLibro) {
            new AddLibroGUI(Main.getLibros()); // Abre la ventana para añadir un libro
        } else if (e.getSource() == btnDelLibro) {
            new EliminarLibroGUI(Main.getLibros()); // Abre la ventana para eliminar un libro
        } else if (e.getSource() == btnGestionarSonido) {
            new GestionarSonidoGUI(); // Abre la ventana para gestionar el sonido
        } else if (e.getSource() == btnGestionarPeticiones) {
            new GestionarPeticionesGUI(Main.getLibros(), Admin.getInstance().getPeticiones()); // Abre la ventana para gestionar peticiones
        } else if (e.getSource() == btnSalir) {
            dispose(); // Cierra la ventana actual
            new InterfazBiblioControl(); // Abre la interfaz de inicio de sesión
        }
    }
}
