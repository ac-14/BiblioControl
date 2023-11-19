package BiblioControl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Clase que representa la interfaz gráfica para reservar un libro
 */
public class ReservarLibroGUI extends JFrame implements ActionListener {
    private JTextField txtISBN;
    private JButton btnReservar, btnCancelar;
    private UsuarioBiblioteca usuarioActual;
    private ArrayList<Libro> biblioteca;

    /**
     * Constructor de la clase ReservarLibroGUI que recibe un usuario y un ArrayList de libros y muestra la interfaz gráfica para reservar un libro
     * @param usuario el usuario
     * @param biblioteca el ArrayList de libros
     */
    public ReservarLibroGUI(UsuarioBiblioteca usuario, ArrayList<Libro> biblioteca) {
        this.usuarioActual = usuario;
        this.biblioteca = biblioteca;

        setTitle("Reservar Libro");
        setSize(350, 120);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(2, 1, 5, 5));

        // Componentes
        txtISBN = new JTextField(15);
        btnReservar = new JButton("Reservar");
        btnCancelar = new JButton("Cancelar");

        // Eventos
        btnReservar.addActionListener(this);
        btnCancelar.addActionListener(this); // Cierra la ventana

        // Añadiendo componentes al panel
        panel.add(new JLabel("ISBN: "));
        panel.add(txtISBN);
        panel.add(btnReservar);
        panel.add(btnCancelar);

        add(panel);

        // Hacer visible la ventana
        setVisible(true);
    }

    /**
     * Método que procesa los eventos de la interfaz gráfica
     * @param e el evento
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnReservar) {
            String isbn = txtISBN.getText();
            String resultadoReserva = usuarioActual.reservarLibro(isbn, biblioteca);
            JOptionPane.showMessageDialog(this, resultadoReserva);
        } else if (e.getSource() == btnCancelar) {
            dispose(); // Cierra la ventana
        }
    }
}