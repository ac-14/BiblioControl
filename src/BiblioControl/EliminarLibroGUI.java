package BiblioControl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Clase que representa la interfaz gráfica para eliminar un libro
 */
public class EliminarLibroGUI extends JFrame implements ActionListener {
    private JTextField txtISBN;
    private JButton btnEliminar, btnCancelar;
    private ArrayList<Libro> Libros;

    /**
     * Constructor de la clase EliminarLibroGUI que recibe un ArrayList de libros y muestra la interfaz gráfica para eliminar un libro
     * @param Libros ArrayList de libros de la biblioteca
     */
    public EliminarLibroGUI(ArrayList<Libro> Libros) {
        this.Libros = Libros;

        // Configuración del JFrame
        setTitle("Eliminar Libro - BiblioControl");
        setSize(350, 120);
        setLocationRelativeTo(null);

        // Crear y configurar JPanel con GridLayout
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2, 10, 10));

        // Etiquetas y campos de texto
        panel.add(new JLabel("ISBN:"));
        txtISBN = new JTextField();
        panel.add(txtISBN);

        // Botones
        btnEliminar = new JButton("Eliminar");
        btnCancelar = new JButton("Cancelar");

        // Añadir botones al panel
        panel.add(btnEliminar);
        panel.add(btnCancelar);

        btnEliminar.addActionListener(this);
        btnCancelar.addActionListener(this);

        add(panel);

        setVisible(true);
    }

    /**
     * Método que procesa los eventos de la interfaz gráfica
     * @param e el evento
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnEliminar) {
            String ISBN = txtISBN.getText();
            String mensaje = Admin.getInstance().eliminarLibro(ISBN, Libros);
            JOptionPane.showMessageDialog(null, mensaje);
        } else if (e.getSource() == btnCancelar) {
            dispose(); // Cierra la ventana
        }
    }
}

