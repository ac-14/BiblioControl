package BiblioControl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Clase que representa la interfaz gráfica para gestionar las peticiones de libros
 */
public class GestionarPeticionesGUI extends JFrame implements ActionListener {
    private JList<String> listPeticiones;
    private JButton btnAñadir, btnEliminar, btnSalir;
    private ArrayList<Libro> libros, peticiones;

    /**
     * Constructor de la clase ManageRequestsGUI
     * @param libros ArrayList de libros
     * @param peticiones ArrayList de peticiones
     */
    public GestionarPeticionesGUI(ArrayList<Libro> libros, ArrayList<Libro> peticiones) {
        this.libros = libros;
        this.peticiones = peticiones;

        setTitle("Gestionar Peticiones - BiblioControl");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        listPeticiones = new JList<>();
        JScrollPane scrollPane = new JScrollPane(listPeticiones);

        actualizarListaPeticiones();

        btnAñadir = new JButton("Añadir Libro");
        btnEliminar = new JButton("Eliminar Petición");
        btnSalir = new JButton("Salir");

        btnAñadir.addActionListener(this);
        btnEliminar.addActionListener(this);
        btnSalir.addActionListener(this);

        JPanel panelBotones = new JPanel();
        panelBotones.add(btnAñadir);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnSalir);

        add(scrollPane, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        setVisible(true);
    }

    /**
     * Método que procesa los eventos de la interfaz gráfica
     * @param e el evento
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAñadir) {
            int selectedIndex = listPeticiones.getSelectedIndex();
            Admin.getInstance().addLibroDePeticiones(peticiones, libros, selectedIndex);
            actualizarListaPeticiones();
        } else if (e.getSource() == btnEliminar) {
            int selectedIndex = listPeticiones.getSelectedIndex();
            Admin.getInstance().eliminarPeticion(peticiones, selectedIndex);
            actualizarListaPeticiones();
        } else if (e.getSource() == btnSalir) {
            dispose();
        }
    }

    /**
     * Método que actualiza la lista de peticiones
     */
    private void actualizarListaPeticiones() {
        String[] peticionesArray = new String[peticiones.size()];
        for (int i = 0; i < peticiones.size(); i++) {
            peticionesArray[i] = peticiones.get(i).getTitulo() + " (ISBN: " + peticiones.get(i).getISBN() + ")";
        }
        listPeticiones.setListData(peticionesArray);
    }
}
