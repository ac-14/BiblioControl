package BiblioControl;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class InterfazUsuario extends JFrame {
    private UsuarioBiblioteca usuarioActual;

    public InterfazUsuario(UsuarioBiblioteca usuario, ArrayList<Libro> libros) {
        this.usuarioActual = usuario;
        setTitle("Usuario - BiblioControl");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(320, 240);
        setLocationRelativeTo(null);

        // Creamos los botones para las acciones del usuario
        JButton btnBuscarLibro = new JButton("Buscar Libro");
        JButton btnSolicitarLibro = new JButton("Solicitar Libro");
        JButton btnVerPrestamos = new JButton("Ver Préstamos");
        JButton btnSalir = new JButton("Salir");

        // Alineamos los botones en el centro
        btnBuscarLibro.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnSolicitarLibro.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnVerPrestamos.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnSalir.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Creamos un panel con BoxLayout vertical
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(btnBuscarLibro);
        panel.add(btnSolicitarLibro);
        panel.add(btnVerPrestamos);
        panel.add(btnSalir);

        getContentPane().add(panel);
        setVisible(true);
    }

    // Método main para probar la interfaz de manera independiente
    public static void main(String[] args) {
        // Crear un usuario de prueba y una lista de libros de prueba
        UsuarioBiblioteca usuarioDePrueba = new UsuarioBiblioteca("12345678A", "UsuarioPrueba", "contraseña", "pista");
        ArrayList<Libro> librosDePrueba = new ArrayList<>();
        librosDePrueba.add(new Libro("1234567890", "El Quijote", "Cervantes"));
        librosDePrueba.add(new Libro("0987654321", "El Señor de los Anillos", "Tolkien"));

        // Aquí pasamos el usuario de prueba y la lista de libros al constructor de InterfazUsuario
        new InterfazUsuario(usuarioDePrueba, librosDePrueba);
    }
}