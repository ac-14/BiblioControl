package BiblioControl;

import javax.swing.*;
import java.awt.*;

public class InterfazBiblioControl extends JFrame {
    public InterfazBiblioControl() {
        setTitle("BiblioControl");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(320, 150);

        // Creamos los botones con las opciones del menú principal
        JButton btnIniciarSesion = new JButton("Iniciar Sesión");
        JButton btnCrearUsuario = new JButton("Crear Usuario");
        JButton btnSalir = new JButton("Salir");

        // Alineamos los botones en el centro
        btnIniciarSesion.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnCrearUsuario.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnSalir.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Creamos un panel con BoxLayout vertical
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(btnIniciarSesion);
        panel.add(btnCrearUsuario);
        panel.add(btnSalir);

        // Añadimos el panel al JFrame
        getContentPane().add(panel);

        setLocationRelativeTo(null);
        setVisible(true);
    }

}
