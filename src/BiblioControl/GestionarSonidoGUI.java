package BiblioControl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Clase que representa la interfaz gráfica para gestionar el sonido
 */
public class GestionarSonidoGUI extends JFrame implements ActionListener {
    public JTextField txtSegundos;
    public JButton btnMedirSonido;
    public JLabel lblNivelSonido;

    /**
     * Constructor de la clase GestionarSonidoGUI
     */
    public GestionarSonidoGUI() {
        setTitle("Gestionar Sonido - BiblioControl");
        setSize(350, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        txtSegundos = new JTextField(10);
        btnMedirSonido = new JButton("Medir Nivel de Sonido");
        lblNivelSonido = new JLabel("Nivel de Sonido: 0 dB");

        btnMedirSonido.addActionListener(this);

        add(new JLabel("Segundos: "));
        add(txtSegundos);
        add(btnMedirSonido);
        add(lblNivelSonido);

        setVisible(true);
    }

    /**
     * Método que procesa los eventos de la interfaz gráfica
     * @param e el evento
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnMedirSonido) {
            Admin.getInstance().medirNivelDeSonido(this);
        }
    }

}