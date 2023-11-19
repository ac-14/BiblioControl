package BiblioControl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Clase que representa la interfaz gráfica para eliminar un usuario
 */
public class EliminarUsuarioGUI extends JFrame implements ActionListener {
    private JTextField txtDNI, txtPasswordAdmin;
    private JButton btnEliminar, btnCancelar;
    private ArrayList<UsuarioBiblioteca> Usuarios;

    /**
     * Constructor de la clase EliminarUsuarioGUI que recibe un ArrayList de usuarios y muestra la interfaz gráfica para eliminar un usuario
     * @param Usuarios ArrayList de usuarios de la biblioteca
     */
    public EliminarUsuarioGUI(ArrayList<UsuarioBiblioteca> Usuarios) {
        this.Usuarios = Usuarios;

        setTitle("Eliminar Usuario - BiblioControl");
        setSize(350, 150);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));

        panel.add(new JLabel("DNI:"));
        txtDNI = new JTextField();
        panel.add(txtDNI);

        panel.add(new JLabel("Contraseña Admin:"));
        txtPasswordAdmin = new JPasswordField();
        panel.add(txtPasswordAdmin);

        btnEliminar = new JButton("Eliminar");
        btnCancelar = new JButton("Cancelar");

        btnEliminar.addActionListener(this);
        btnCancelar.addActionListener(this);

        panel.add(btnEliminar);
        panel.add(btnCancelar);

        add(panel);

        setVisible(true);
    }

    /**
     * Método que procesa los eventos de la interfaz gráfica
     * @param e el evento
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnEliminar) {
            String DNI = txtDNI.getText();
            String password = new String(txtPasswordAdmin.getText());
            String resultado = Admin.getInstance().delUsuario(Usuarios, DNI, password);
            JOptionPane.showMessageDialog(null, resultado);
        } else if (e.getSource() == btnCancelar) {
            dispose(); // Cierra la ventana
        }
    }
}
