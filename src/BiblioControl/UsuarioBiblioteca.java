package BiblioControl;

import java.util.ArrayList;

/**
 * Clase Usuario
 * Contiene los atributos de un usuario y sus metodos get y set ademas del menu de usuario
 */
public class UsuarioBiblioteca extends Usuario implements Menus{
    private ArrayList<Libro> librosReservados = new ArrayList<>();

    /**
     * Constructor de la clase Usuario
     * @param DNI DNI del usuario
     * @param nombre Nombre del usuario
     * @param password Contraseña del usuario
     * @param pistaPassword Pista de la contraseña del usuario
     */
    public UsuarioBiblioteca(String DNI, String nombre, String password, String pistaPassword) {
        super(DNI, nombre, password, pistaPassword);
    }


    /**
     * Método para reservar un libro por su ISBN.
     * @param isbn ISBN del libro a reservar.
     * @param biblioteca Lista de libros en la biblioteca.
     * @return Un mensaje indicando el resultado de la reserva.
     */
    public String reservarLibro(String isbn, ArrayList<Libro> biblioteca) {
        for (Libro libro : biblioteca) {
            if (libro.getISBN().equals(isbn) && libro.getDisponible()) {
                librosReservados.add(libro);
                biblioteca.remove(libro);
                libro.setDisponible(false);
                return "Has reservado el libro: " + libro.getTitulo();
            }
        }
        return "El libro no está disponible o no existe.";
    }

    /**
     * Método para devolver un libro por ISBN.
     * @param isbn ISBN del libro a devolver.
     * @param biblioteca Biblioteca de la que se devuelve el libro.
     * @return Mensaje con el resultado de la operación.
     */
    public String devolverLibro(String isbn, ArrayList<Libro> biblioteca) {
        for (Libro libro : librosReservados) {
            if (libro.getISBN().equals(isbn)) {
                librosReservados.remove(libro);
                biblioteca.add(libro);
                libro.setDisponible(true);
                return "Has devuelto el libro: " + libro.getTitulo();
            }
        }
        return "No has reservado este libro o no existe.";
    }

    /**
     * Método para solicitar un libro por su ISBN, título y autor.
     * @param isbn ISBN del libro a solicitar.
     * @param titulo Título del libro a solicitar.
     * @param autor Autor del libro a solicitar.
     * @param biblioteca Lista de libros en la biblioteca.
     * @return Un mensaje indicando el resultado de la solicitud.
     */
    public String solicitarLibro(String isbn, String titulo, String autor, ArrayList<Libro> biblioteca) {
        // Comprobar si el libro existe y está disponible en la biblioteca
        for (Libro libro : biblioteca) {
            if (libro.getISBN().equals(isbn)) {
                // Si el libro está disponible, no permitir la solicitud
                if (libro.getDisponible()) {
                    return "El libro " + libro.getTitulo() + " ya está disponible y no necesita ser solicitado.";
                }
            }
        }
        // El libro no existe en la biblioteca o no está disponible, se puede solicitar
        Libro libroSolicitado = new Libro(isbn, titulo, autor);
        Admin.getInstance().addPeticion(libroSolicitado);
        return "Has solicitado el libro: " + titulo;
    }

    /**
     * Método que cambia la contraseña del usuario actual
     * @param passwordActual La contraseña actual
     * @param passwordNueva La nueva contraseña
     */
    public void cambiarPassword(String passwordActual, String passwordNueva) throws PasswordIncorrectaException {
        if (!ComprobarPassword(passwordActual)) {
            throw new PasswordIncorrectaException("Contraseña actual incorrecta");
        }
        setPassword(passwordNueva);
        System.out.println("Contraseña cambiada correctamente");
    }

    /**
     * Excepción propia para cuando la contraseña actual es incorrecta
     */
    public class PasswordIncorrectaException extends Exception {
        public PasswordIncorrectaException(String mensaje) {
            super(mensaje);
        }
    }

    /**
     * Método que busca un usuario por su DNI
     * @param DNI DNI del usuario
     * @param Usuarios ArrayList de usuarios
     * @return El usuario encontrado o null si no se encuentra
     */
    public static UsuarioBiblioteca buscarUsuarioPorDNI(String DNI, ArrayList<UsuarioBiblioteca> Usuarios) {
        if (Usuarios == null) {
            return null;
        }
        for (UsuarioBiblioteca usuario : Usuarios) {
            if (usuario.getDNI().equals(DNI)) {
                return usuario;
            }
        }
        return null;
    }
    /**
     * Metodo Menu
     * Inicia una interfaz grafica para el usuario
     * @param Usuarios ArrayList de usuarios
     * @param Libros ArrayList de libros
     */
    public void Menu(ArrayList<UsuarioBiblioteca> Usuarios, ArrayList<Libro> Libros) {
        new InterfazUsuario(this); // Abre la interfaz gráfica del usuario
    }
}