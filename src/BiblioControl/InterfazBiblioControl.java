package BiblioControl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Clase que representa la interfaz gráfica para el menú principal desde el que se puede iniciar sesión o crear un usuario
 */
public class InterfazBiblioControl extends JFrame implements ActionListener {
    private JButton btnIniciarSesion, btnCrearUsuario, btnCerrar;

    /**
     * Constructor de la clase InterfazBiblioControl que muestra la interfaz gráfica para el menú principal
     */
    public InterfazBiblioControl() {
        // Configuración del JFrame
        setTitle("BiblioControl - Menú Principal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(180, 120);

        // Creando botones y configurando su alineación
        btnIniciarSesion = new JButton("Iniciar Sesión");
        btnCrearUsuario = new JButton("Crear Usuario");
        btnCerrar = new JButton("Cerrar");
        btnIniciarSesion.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnCrearUsuario.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnCerrar.setAlignmentX(Component.CENTER_ALIGNMENT);

        btnIniciarSesion.addActionListener(this);
        btnCrearUsuario.addActionListener(this);
        btnCerrar.addActionListener(this);

        // Creando y configurando un JPanel con BoxLayout
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Añadiendo botones al panel
        panel.add(btnIniciarSesion);
        panel.add(btnCrearUsuario);
        panel.add(btnCerrar);

        // Añadiendo el panel al JFrame
        getContentPane().add(panel);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Método que procesa los eventos de la interfaz gráfica
     * @param e el evento
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnIniciarSesion) {
            new IniciarSesionGUI();
        } else if (e.getSource() == btnCrearUsuario) {
            new AddUsuarioGUI(Admin.getInstance().getUsuarios());
        } else if (e.getSource() == btnCerrar) {
            System.exit(0);
        }
    }
}
