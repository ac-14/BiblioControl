package BiblioControl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Clase que representa la interfaz gráfica para solicitar un libro
 */
public class SolicitarLibroGUI extends JFrame implements ActionListener {
    private JTextField txtISBN, txtTitulo, txtAutor;
    private JButton btnSolicitar, btnCancelar;
    private UsuarioBiblioteca usuarioActual;
    private ArrayList<Libro> biblioteca;

    /**
     * Constructor de la clase SolicitarLibroGUI
     * @param usuario Usuario que solicita el libro
     * @param biblioteca ArrayList de libros
     */
    public SolicitarLibroGUI(UsuarioBiblioteca usuario, ArrayList<Libro> biblioteca) {
        this.usuarioActual = usuario;
        this.biblioteca = biblioteca;

        setTitle("Solicitar Libro");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 250);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));

        txtISBN = new JTextField(15);
        txtTitulo = new JTextField(15);
        txtAutor = new JTextField(15);
        btnSolicitar = new JButton("Solicitar");
        btnCancelar = new JButton("Cancelar");

        btnSolicitar.addActionListener(this);
        btnCancelar.addActionListener(this);

        panel.add(new JLabel("ISBN: "));
        panel.add(txtISBN);
        panel.add(new JLabel("Título: "));
        panel.add(txtTitulo);
        panel.add(new JLabel("Autor: "));
        panel.add(txtAutor);
        panel.add(btnSolicitar);
        panel.add(btnCancelar);

        getContentPane().add(panel);
        setVisible(true);
    }

    /**
     * Método que procesa los eventos de la interfaz gráfica
     * @param e el evento
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSolicitar) {
            // Solicitar el libro y obtener el mensaje
            String mensaje = usuarioActual.solicitarLibro(txtISBN.getText(), txtTitulo.getText(), txtAutor.getText(), biblioteca);
            // Mostrar el mensaje en un cuadro de diálogo
            JOptionPane.showMessageDialog(this, mensaje);
        }
        else if (e.getSource() == btnCancelar) {
            dispose(); // Cierra la ventana
        }
    }
}
