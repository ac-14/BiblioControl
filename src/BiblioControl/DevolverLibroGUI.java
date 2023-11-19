package BiblioControl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Clase que representa la interfaz gráfica para devolver un libro
 */
public class DevolverLibroGUI extends JFrame implements ActionListener {
    private JTextField txtISBN;
    private JButton btnDevolver, btnCancelar;
    private UsuarioBiblioteca usuarioActual;
    private ArrayList<Libro> biblioteca;

    /**
     * Constructor de la clase DevolverLibroGUI
     * @param usuario Usuario que devuelve el libro
     * @param biblioteca ArrayList de libros
     */
    public DevolverLibroGUI(UsuarioBiblioteca usuario, ArrayList<Libro> biblioteca) {
        this.usuarioActual = usuario;
        this.biblioteca = biblioteca;

        setTitle("Devolver Libro");
        setSize(300, 120);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(2, 2, 5, 5)); // GridLayout con 2 filas y 2 columnas

        // Componentes
        txtISBN = new JTextField(15);
        btnDevolver = new JButton("Devolver");
        btnCancelar = new JButton("Cancelar");

        // Eventos
        btnDevolver.addActionListener(this);
        btnCancelar.addActionListener(this);

        // Añadir componentes al panel
        panel.add(new JLabel("ISBN: "));
        panel.add(txtISBN);
        panel.add(btnDevolver);
        panel.add(btnCancelar);

        // Añadir el panel al JFrame
        add(panel);
        setVisible(true);
    }

    /**
     * Método que procesa los eventos de la interfaz gráfica
     * @param e el evento
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnDevolver) {
            String mensaje = usuarioActual.devolverLibro(txtISBN.getText(), biblioteca);
            JOptionPane.showMessageDialog(this, mensaje);
        }
        else if (e.getSource() == btnCancelar) {
            dispose(); // Cierra la ventana
        }
    }
}