package BiblioControl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Clase que representa la interfaz gráfica para solicitar un libro
 */
public class SolicitarLibroGUI extends JFrame implements ActionListener {
    private JTextField txtTitulo;
    private JButton btnBuscar, btnCancelar, btnSolicitar;
    private JList<String> listResultados;
    private UsuarioBiblioteca usuarioActual;
    private ArrayList<Libro> biblioteca;
    private ArrayList<Libro> resultadosBusqueda; // Almacenar los resultados de la búsqueda

    /**
     * Constructor de la clase SolicitarLibroGUI
     * @param usuario Usuario actual donde se guardara el libro reservado
     * @param biblioteca ArrayList de libros de la biblioteca
     */
    public SolicitarLibroGUI(UsuarioBiblioteca usuario, ArrayList<Libro> biblioteca) {
        this.usuarioActual = usuario;
        this.biblioteca = biblioteca;
        this.resultadosBusqueda = new ArrayList<>();

        setTitle("Solicitar Libro");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel Superior
        JPanel panelSuperior = new JPanel(new GridLayout(1, 2, 10, 10));
        panelSuperior.add(new JLabel("Título:"));
        txtTitulo = new JTextField();
        panelSuperior.add(txtTitulo);
        btnBuscar = new JButton("Buscar");
        btnBuscar.addActionListener(this);
        panelSuperior.add(btnBuscar);
        add(panelSuperior, BorderLayout.NORTH);

        // Lista de Resultados
        listResultados = new JList<>();
        add(new JScrollPane(listResultados), BorderLayout.CENTER);

        // Panel de Botones
        JPanel panelBotones = new JPanel();
        btnSolicitar = new JButton("Solicitar");
        btnCancelar = new JButton("Cancelar");
        btnSolicitar.addActionListener(this);
        btnCancelar.addActionListener(this);
        panelBotones.add(btnSolicitar);
        panelBotones.add(btnCancelar);
        add(panelBotones, BorderLayout.SOUTH);

        setVisible(true);
        setLocationRelativeTo(null);
    }

    /**
     * Método que procesa los eventos de los botones
     * @param e el evento
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnBuscar) {
            String titulo = txtTitulo.getText();
            resultadosBusqueda = BusquedaInternet.buscarPorTitulo(titulo);
            actualizarListaResultados();
        } else if (e.getSource() == btnCancelar) {
            dispose();
        } else if (e.getSource() == btnSolicitar) {
            int selectedIndex = listResultados.getSelectedIndex();
            if (selectedIndex != -1) {
                Libro libroSeleccionado = resultadosBusqueda.get(selectedIndex);
                String isbn = libroSeleccionado.getISBN();
                String titulo = libroSeleccionado.getTitulo();
                String autor = libroSeleccionado.getAutor();

                String mensaje = usuarioActual.solicitarLibro(isbn, titulo, autor, biblioteca);
                JOptionPane.showMessageDialog(this, mensaje);
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, seleccione un libro de la lista");
            }
        }
    }

    /**
     * Método que actualiza la lista de resultados de búsqueda
     */
    public void actualizarListaResultados() {
        String[] resultadosArray = new String[resultadosBusqueda.size()];
        for (int i = 0; i < resultadosBusqueda.size(); i++) {
            Libro libro = resultadosBusqueda.get(i);
            resultadosArray[i] = libro.getTitulo() + " - ISBN: " + libro.getISBN();
        }
        listResultados.setListData(resultadosArray);
    }
}
