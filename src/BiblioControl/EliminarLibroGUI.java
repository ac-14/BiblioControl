package BiblioControl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Clase EliminarLibroGUI que permite al admin eliminar un libro de la biblioteca.
 */
public class EliminarLibroGUI extends JFrame implements ActionListener {
    private JList<String> listLibros;
    private JButton btnEliminar, btnCancelar;
    private ArrayList<Libro> Libros;

    /**
     * Constructor de la clase EliminarLibroGUI.
     * @param Libros ArrayList de Libro que representa la biblioteca.
     */
    public EliminarLibroGUI(ArrayList<Libro> Libros) {
        this.Libros = Libros;

        // Configuración del JFrame
        setTitle("Eliminar Libro");
        setSize(350, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        listLibros = new JList<>();
        JScrollPane scrollPane = new JScrollPane(listLibros);

        actualizarListaLibros();
        btnEliminar = new JButton("Eliminar");
        btnCancelar = new JButton("Cancelar");

        btnEliminar.addActionListener(this);
        btnCancelar.addActionListener(this);

        JPanel panelBotones = new JPanel();
        panelBotones.add(btnEliminar);
        panelBotones.add(btnCancelar);

        add(scrollPane, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        setVisible(true);
        setLocationRelativeTo(null);
    }

    /**
     * Método que procesa los eventos de la interfaz gráfica.
     * @param e el evento
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnEliminar) {
            int selectedIndex = listLibros.getSelectedIndex();
            // Si se ha seleccionado un libro, se elimina
            if (selectedIndex != -1) {
                Libro libroSeleccionado = Libros.get(selectedIndex);
                String mensaje = Admin.getInstance().eliminarLibro(libroSeleccionado.getISBN(), Libros);
                GestorDeArchivos.guardarLibros(Libros);
                actualizarListaLibros();
                JOptionPane.showMessageDialog(null, mensaje);
            } else {
                JOptionPane.showMessageDialog(null, "Por favor, seleccione un libro para eliminar.");
            }
        } else if (e.getSource() == btnCancelar) {
            dispose();
        }
    }

    /**
     * Método para actualizar la lista de libros en la interfaz gráfica.
     */
    private void actualizarListaLibros() {
        // Convertimos el ArrayList de libros a un array de Strings
        String[] librosArray = new String[Libros.size()];
        for (int i = 0; i < Libros.size(); i++) {
            librosArray[i] = Libros.get(i).getTitulo() + " (ISBN: " + Libros.get(i).getISBN() + ")";
        }
        listLibros.setListData(librosArray);
    }
}