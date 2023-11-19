package BiblioControl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Clase que representa la interfaz gráfica para añadir un libro
 */
public class AddLibroGUI extends JFrame implements ActionListener {
    private JTextField txtISBN, txtTitulo, txtAutor;
    private JButton btnGuardar, btnCancelar;
    private ArrayList<Libro> Libros;

    /**
     * Constructor de la clase AddLibroGUI que recibe un ArrayList de libros y muestra la interfaz gráfica para añadir un libro
     * @param Libros ArrayList de libros de la biblioteca
     */
    public AddLibroGUI(ArrayList<Libro> Libros) {
        // Inicializar variables
        this.Libros = Libros;

        // Titulo y tamaño de la ventana
        setTitle("Añadir Libro - BiblioControl");
        setSize(350, 200);

        // Crear y configurar JPanel con GridLayout
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        setContentPane(panel);

        // Etiquetas y campos de texto
        panel.add(new JLabel("ISBN:"));
        txtISBN = new JTextField();
        panel.add(txtISBN);

        panel.add(new JLabel("Título:"));
        txtTitulo = new JTextField();
        panel.add(txtTitulo);

        panel.add(new JLabel("Autor:"));
        txtAutor = new JTextField();
        panel.add(txtAutor);

        // Botones
        btnGuardar = new JButton("Guardar");
        btnCancelar = new JButton("Cancelar");

        panel.add(btnGuardar);
        panel.add(btnCancelar);

        btnGuardar.addActionListener(this);
        btnCancelar.addActionListener(this);

        setVisible(true);
        setLocationRelativeTo(null);
    }

    /**
     * Método que procesa los eventos de la interfaz gráfica
     * @param e el evento
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnGuardar) {
            String ISBN = txtISBN.getText();
            String titulo = txtTitulo.getText();
            String autor = txtAutor.getText();

            // Bloque try-catch para manejar excepciones
            try {
                // Si el libro ya existe en la biblioteca, lanzar una excepción
                if (Admin.getInstance().libroExiste(ISBN, Libros)) {
                    throw new Exception("El libro con este ISBN ya existe en la biblioteca.");
                }
                // Si no, se crea un objeto Libro y se añade al ArrayList de libros
                Libro nuevoLibro = new Libro(ISBN, titulo, autor);
                Libros.add(nuevoLibro);
                JOptionPane.showMessageDialog(null, "Libro añadido con éxito");
                dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        } else if (e.getSource() == btnCancelar) {
            dispose();
        }
    }
}
