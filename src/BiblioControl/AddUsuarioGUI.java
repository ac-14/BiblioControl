package BiblioControl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Clase que representa la interfaz gráfica para añadir un usuario
 */
public class AddUsuarioGUI extends JFrame implements ActionListener {
    private JTextField txtDNI, txtNombre, txtPassword, txtPistaPassword;
    private JButton btnGuardar, btnCancelar;
    private ArrayList<UsuarioBiblioteca> Usuarios;

    /**
     * Constructor de la clase AddUsuarioGUI que recibe un ArrayList de usuarios y muestra la interfaz gráfica para añadir un usuario
     * @param Usuarios ArrayList de usuarios de la biblioteca
     */
    public AddUsuarioGUI(ArrayList<UsuarioBiblioteca> Usuarios) {
        this.Usuarios = Usuarios;
        setTitle("Añadir Usuario - BiblioControl");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(350, 280);
        setLocationRelativeTo(null);

        // Crear y configurar JPanel con GridLayout
        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        setContentPane(panel);

        // Etiquetas y campos de texto
        panel.add(new JLabel("DNI:"));
        txtDNI = new JTextField();
        panel.add(txtDNI);

        panel.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        panel.add(txtNombre);

        panel.add(new JLabel("Contraseña:"));
        txtPassword = new JTextField();
        panel.add(txtPassword);

        panel.add(new JLabel("Pista Contraseña:"));
        txtPistaPassword = new JTextField();
        panel.add(txtPistaPassword);

        // Botones
        btnGuardar = new JButton("Guardar");
        btnCancelar = new JButton("Cancelar");

        panel.add(btnGuardar);
        panel.add(btnCancelar);

        btnGuardar.addActionListener(this);
        btnCancelar.addActionListener(this);

        setVisible(true);
    }

    /**
     * Método que procesa los eventos de la interfaz gráfica
     * @param e el evento
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnGuardar) {
            try {
                String DNI = txtDNI.getText();
                String nombre = txtNombre.getText();
                String password = txtPassword.getText();
                String pistaPassword = txtPistaPassword.getText();

                Admin.getInstance().addUsuario(DNI, nombre, password, pistaPassword, Usuarios);
                JOptionPane.showMessageDialog(null, "Usuario creado con éxito");
                dispose();
            } catch (Admin.DNIInvalidoException | Admin.UsuarioYaExisteException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        } else if (e.getSource() == btnCancelar) {
            dispose();
        }
    }
}