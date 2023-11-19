package BiblioControl;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

// Bibliotecas para el sonido
import javax.sound.sampled.*;

/**
 * Clase Admin que hereda de Usuario.
 * Contiene los metodos para añadir y eliminar usuarios y libros, y para gestionar el sonido de la sala y las peticiones.
 */
    public class Admin extends Usuario implements Menus {
    private static Admin instance;
    private static ArrayList<Libro> peticiones = new ArrayList<>();
    /**
     * Constructor
     * @param DNI DNI del administrador
     * @param password Contraseña del administrador
     */
    public Admin(String DNI, String password){
        super(DNI, "", password, "");
    }

    /**
     * Metodo para obtener la instancia de Admin (Singleton)
     * @return devuelve la instancia de Admin
     */
    public static Admin getInstance() {
        if (instance == null) {
            instance = new Admin("admin", "adminpassword");
        }
        return instance;
    }
    /**
     * Metodo para añadir un usuario
     * @param DNI DNI del usuario
     * @param nombre Nombre del usuario
     * @param password Contraseña del usuario
     * @param pistaPassword Pista de la contraseña del usuario
     * @param Usuarios ArrayList de usuarios de la biblioteca
     */
    public void addUsuario(String DNI, String nombre, String password, String pistaPassword, ArrayList<UsuarioBiblioteca> Usuarios) throws DNIInvalidoException, UsuarioYaExisteException {
        // Validar el DNI
        if (!UsuarioBiblioteca.validarDNI(DNI)) {
            throw new DNIInvalidoException("El DNI introducido no es válido.");
        }
        // Comprobar si el usuario ya existe
        for (UsuarioBiblioteca usuario : Usuarios) {
            if (usuario.getDNI().equals(DNI)) {
                throw new UsuarioYaExisteException("Un usuario con este DNI ya existe.");
            }
        }
        // Crear un nuevo usuario y agregarlo a la lista
        UsuarioBiblioteca nuevoUsuario = new UsuarioBiblioteca(DNI, nombre, password, pistaPassword);
        Usuarios.add(nuevoUsuario);
    }

    /**
     * Método para eliminar un usuario.
     * @param Usuarios ArrayList de usuarios de la biblioteca.
     * @param DNI DNI del usuario a eliminar.
     * @param password Contraseña del administrador.
     * @return Mensaje indicando el resultado de la operación.
     */
    public String delUsuario(ArrayList<UsuarioBiblioteca> Usuarios, String DNI, String password) {
        if (this.ComprobarPassword(password)) {
            boolean usuarioEncontrado = false;
            for (int i = 0; i < Usuarios.size(); i++) {
                if (Usuarios.get(i).getDNI().equals(DNI)) {
                    Usuarios.remove(i);
                    usuarioEncontrado = true;
                    break;
                }
            }
            if (usuarioEncontrado) {
                return "Usuario eliminado con éxito";
            } else {
                return "Usuario no encontrado";
            }
        } else {
            return "Contraseña del administrador incorrecta";
        }
    }
    /**
     * Método para eliminar un libro del ArrayList de libros.
     * @param isbn ISBN del libro a eliminar.
     * @param libros ArrayList de libros de la biblioteca.
     * @return Mensaje indicando el resultado de la operación.
     */
    public String eliminarLibro(String isbn, ArrayList<Libro> libros) {
        for (int i = 0; i < libros.size(); i++) {
            if (libros.get(i).getISBN().equals(isbn)) {
                libros.remove(i);
                return "Libro eliminado con éxito";
            }
        }
        return "No se encontró un libro con ese ISBN";
    }

    /**
     * Metodo autenticarAdmin
     * Comprueba si el DNI y la contraseña introducidos son correctos
     * @param password Contraseña del administrador
     * @return devuelve true si el usuario y la contraseña son correctos, false si no lo son
     */
    public boolean autenticarAdmin(String password) {
        return this.ComprobarPassword(password);
    }

    /**
     * Excepcion para cuando no hay peticiones
     */
    public class SinPeticionesException extends Exception {
        public SinPeticionesException(String mensaje) {
            super(mensaje);
        }
    }

    /**
     * Metodo para añadir peticiones de libros
     * @param libro Libro que se va a añadir a las peticiones
     */
    public void addPeticion(Libro libro) {
        peticiones.add(libro);
    }

    /**
     * Metodo para obtener las peticiones
     * @return devuelve las peticiones
     */
    public ArrayList<Libro> getPeticiones() {
        return peticiones;
    }

    /**
     * Método para añadir un libro desde las peticiones a la lista de libros.
     * @param peticiones ArrayList de peticiones.
     * @param libros ArrayList de libros.
     * @param selectedIndex Índice del libro seleccionado en peticiones.
     */
    public void addLibroDePeticiones(ArrayList<Libro> peticiones, ArrayList<Libro> libros, int selectedIndex) {
        if (selectedIndex != -1) {
            Libro libroSeleccionado = peticiones.get(selectedIndex);
            libros.add(libroSeleccionado);
            peticiones.remove(selectedIndex);
        }
    }

    /**
     * Método para eliminar una petición.
     * @param peticiones ArrayList de peticiones.
     * @param index Índice de la petición seleccionada.
     */
    public void eliminarPeticion(ArrayList<Libro> peticiones, int index) {
        if (index != -1) {
            peticiones.remove(index);
        }
    }

    /**
     * Metodo para medir el nivel de sonido de la sala
     */
    public void medirNivelDeSonido(GestionarSonidoGUI gui) {
        new Thread(() -> {
            try {
                int segundos = Integer.parseInt(gui.txtSegundos.getText());
                AudioFormat format = new AudioFormat(44100, 16, 1, true, true);
                DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);

                if (!AudioSystem.isLineSupported(info)) {
                    SwingUtilities.invokeLater(() -> {
                        JOptionPane.showMessageDialog(gui, "Micrófono no soportado", "Error", JOptionPane.ERROR_MESSAGE);
                    });
                    return;
                }

                TargetDataLine line = (TargetDataLine) AudioSystem.getLine(info);
                line.open(format);
                line.start();

                byte[] buffer = new byte[1024];
                long endTime = System.currentTimeMillis() + segundos * 1000;
                double umbral = 40.0; // Umbral de sonido en dB

                while (System.currentTimeMillis() < endTime) {
                    int bytesRead = line.read(buffer, 0, buffer.length);
                    double level = calculateLevel(buffer, bytesRead);
                    SwingUtilities.invokeLater(() -> {
                        if (level > umbral) {
                            gui.lblNivelSonido.setForeground(Color.RED);
                            gui.lblNivelSonido.setText("SILENCIO, POR FAVOR - " + String.format("%.2f dB", level));
                        } else {
                            gui.lblNivelSonido.setForeground(Color.BLACK);
                            gui.lblNivelSonido.setText("Nivel de Sonido: " + String.format("%.2f dB", level));
                        }
                    });
                }

                line.stop();
                line.close();

            } catch (NumberFormatException ex) {
                SwingUtilities.invokeLater(() -> {
                    JOptionPane.showMessageDialog(gui, "Por favor, ingresa un número válido de segundos.", "Error de Entrada", JOptionPane.ERROR_MESSAGE);
                });
            } catch (LineUnavailableException ex) {
                SwingUtilities.invokeLater(() -> {
                    JOptionPane.showMessageDialog(gui, "Línea de audio no disponible.", "Error", JOptionPane.ERROR_MESSAGE);
                });
            }
        }).start();
    }

    /**
     * Metodo para calcular el nivel de sonido
     * Como el sonido no se obtiene directamente en decibelios, se calcula el valor RMS (Root Mean Square) de la señal de audio y se convierte a decibelios
     * @param buffer Array de bytes que contiene los datos de audio del micrófono
     * @param bytesRead Número de bytes leídos del micrófono en el último ciclo
     * @return devuelve el nivel de sonido en decibelios
     */
    private static double calculateLevel(byte[] buffer, int bytesRead) {
        double sum = 0.0;

        for (int i = 0; i < bytesRead; i++) {
            sum += buffer[i] * buffer[i];
        }
        // Calcular el valor RMS (Root Mean Square) de la señal de audio y convertirlo a decibelios
        double rms = Math.sqrt(sum / bytesRead);
        double dB = 20 * Math.log10(rms);
        return dB;
    }

    /**
     * Excepcion para cuando el DNI no es valido
     */
    public static class DNIInvalidoException extends Exception {
        public DNIInvalidoException(String mensaje) {
            super(mensaje);
        }
    }

    /**
     * Excepcion para cuando el usuario ya existe
     */
    public static class UsuarioYaExisteException extends Exception {
        public UsuarioYaExisteException(String mensaje) {
            super(mensaje);
        }
    }

    /**
     * Metodo para comprobar si un libro existe
     * @param isbn ISBN del libro
     * @param libros ArrayList de libros de la biblioteca
     * @return devuelve true si el libro existe, false si no existe
     */
    public boolean libroExiste(String isbn, ArrayList<Libro> libros) {
        for (Libro libro : libros) {
            if (libro.getISBN().equals(isbn)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Metodo que muestra el menu de administrador
     * Desde aqui se pueden añadir y eliminar usuarios y libros, gestionar el sonido de la sala y las peticiones
     * @param Usuarios ArrayList de usuarios de la biblioteca
     * @param Libros ArrayList de libros de la biblioteca
     */
    public void Menu(ArrayList<UsuarioBiblioteca> Usuarios, ArrayList<Libro> Libros){
        new InterfazAdmin();
    }
}