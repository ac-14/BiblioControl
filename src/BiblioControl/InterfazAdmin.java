package BiblioControl;

import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;

public class InterfazAdmin extends JFrame {
    public InterfazAdmin(ArrayList<UsuarioBiblioteca> Usuarios, ArrayList<Libro> Libros) {
        setTitle("Administración - BiblioControl");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(320, 240);
        setLocationRelativeTo(null);

        // Creamos los botones para las acciones de admin
        JButton btnAddUsuario = new JButton("Añadir usuario");
        JButton btnDelUsuario = new JButton("Eliminar usuario");
        JButton btnAddLibro = new JButton("Añadir libro");
        JButton btnDelLibro = new JButton("Eliminar libro");
        JButton btnGestionarSonido = new JButton("Gestionar sonido");
        JButton btnGestionarPeticiones = new JButton("Gestionar peticiones");
        JButton btnSalir = new JButton("Salir");

        // Alineamos los botones en el centro
        btnAddUsuario.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnDelUsuario.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnAddLibro.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnDelLibro.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnGestionarSonido.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnGestionarPeticiones.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnSalir.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Crear un panel con BoxLayout vertical
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Añadir los botones al panel
        panel.add(btnAddUsuario);
        panel.add(btnDelUsuario);
        panel.add(btnAddLibro);
        panel.add(btnDelLibro);
        panel.add(btnGestionarSonido);
        panel.add(btnGestionarPeticiones);
        panel.add(btnSalir);

        getContentPane().add(panel);
        setVisible(true);
    }

    // Por ahora lo probamos aqui
    public static void main(String[] args) {
        new InterfazAdmin(new ArrayList<UsuarioBiblioteca>(), new ArrayList<Libro>());
    }
}
