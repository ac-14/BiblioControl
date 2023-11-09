package BiblioControl;


import java.util.ArrayList;
import java.util.Scanner;

/**
 * Clase Usuario
 * Contiene los atributos de un usuario y sus metodos get y set ademas del menu de usuario
 */
public class Usuario {
    private String DNI;
    private String nombre;
    private String password;
    private String pistaPassword;
    private ArrayList<Libro> librosReservados = new ArrayList<>();

    /**
     * Constructor de la clase Usuario
     * @param DNI DNI del usuario
     * @param nombre Nombre del usuario
     * @param password Contraseña del usuario
     * @param pistaPassword Pista de la contraseña del usuario
     */
    public Usuario(String DNI, String nombre, String password, String pistaPassword) {
        this.DNI = DNI;
        this.nombre = nombre;
        this.password = password;
        this.pistaPassword = pistaPassword;
    }

    /**
     * Metodo getDNI
     * @return devuelve el DNI del usuario
     */
    public String getDNI() {
        return DNI;
    }

    /**
     * Metodo setDNI
     * @return DNI del usuario
     */
    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    /**
     * Metodo getNombre
     * @return devuelve el nombre del usuario
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Metodo setNombre
     * @param nombre Nombre del usuario
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Metodo setPassword
     * @param password Contraseña del usuario
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Metodo getPassword
     * @return devuelve la contraseña del usuario
     */
    public String getPassword() {
        return password;
    }

    /**
     * Metodo setPistaPassword
     * @param pistaPassword Pista de la contraseña del usuario
     */
    public void setPistaPassword(String pistaPassword) {
        this.pistaPassword = pistaPassword;
    }

    /**
     * Metodo getPistaPassword
     * @return devuelve la pista de la contraseña del usuario
     */
    public String getPistaPassword() {
        return this.pistaPassword;
    }

    /**
     * Metodo reservarLibro
     * @param libro Libro que se va a reservar
     * @param biblioteca ArrayList de libros
     */
    public void reservarLibro(Libro libro, ArrayList<Libro> biblioteca) {
        if (libro.getDisponible()) {
            librosReservados.add(libro);
            biblioteca.remove(libro);
            libro.setDisponible(false);
            System.out.println("Has reservado el libro: " + libro.getTitulo());
        } else {
            System.out.println("El libro no está disponible.");
        }
    }

    /**
     * Metodo devolverLibro
     * @param libro Libro que se va a devolver
     * @param biblioteca ArrayList de libros
     */
    public void devolverLibro(Libro libro, ArrayList<Libro> biblioteca) {
        if (librosReservados.contains(libro)) {
            librosReservados.remove(libro);
            biblioteca.add(libro);
            libro.setDisponible(true);
            System.out.println("Has devuelto el libro: " + libro.getTitulo());
        } else {
            System.out.println("No has reservado este libro.");
        }
    }

    /**
     * Metodo ComprobarPassword
     * @param password
     * @return devuelve true si la contraseña es correcta
     */
     public boolean ComprobarPassword(String password) {
        return this.password.equals(password);
    }

    /**
     * Metodo validarDNI
     * @param DNI DNI del usuario
     * @return devuelve true si el DNI es correcto
     */
    public static boolean validarDNI(String DNI) {
        if (DNI.length() != 9) {
            return false; // Si la longitud no es 9, no es un DNI válido.
        }
        for (int i = 0; i < 8; i++) {
            if (DNI.charAt(i) < '0' || DNI.charAt(i) > '9') {
                return false; // Si algún carácter no es un dígito, no es un DNI válido.
            }
        }
        char letra = DNI.charAt(8);
        return Character.isLetter(letra); // Devuelve true si el último carácter es una letra.
    }

    /**
     * Metodo solicitarLibro
     * Añade una peticion de libro a la lista de peticiones
     * @param ISBN ISBN del libro
     * @param titulo Titulo del libro
     * @param autor Autor del libro
     * @param biblioteca ArrayList de libros
     */
    public void solicitarLibro(String ISBN, String titulo, String autor, ArrayList<Libro> biblioteca) {
        // Comprobar si el libro existe y está disponible en la biblioteca
        for (Libro libro : biblioteca) {
            if (libro.getISBN().equals(ISBN)) {
                // Si el libro está disponible, no permitir la solicitud
                if (libro.getDisponible()) {
                    System.out.println("El libro " + libro.getTitulo() + " ya está disponible y no necesita ser solicitado.");
                    return;
                }
            }
        }
        Libro libroSolicitado = new Libro(ISBN, titulo, autor);
        Admin.addPeticion(libroSolicitado);
    }

    /**
     * Metodo MenuUsuario
     * Muestra el menu de usuario y sus opciones
     * @param biblioteca ArrayList de libros
     */
    public void MenuUsuario(ArrayList<Libro> biblioteca) {
        boolean salir = false;
        Scanner teclado = new Scanner(System.in);
        int opcion;

        while (!salir) {
            System.out.println("\nMENU USUARIO");
            System.out.println("1. Reservar libro");
            System.out.println("2. Solicitar libro");
            System.out.println("3. Devolver libro");
            System.out.println("4. Cambiar contraseña");
            System.out.println("5. Salir");
            System.out.print("Escribe una de las opciones: ");
            opcion = teclado.nextInt();

            switch (opcion) {
                case 1:
                    // Reservar libro
                    System.out.println("Introduce el ISBN del libro que deseas reservar: ");
                    String ISBNReserva = teclado.next();

                    Libro libroReserva = null;
                    for (Libro libro : biblioteca) {
                        if (libro.getISBN().equals(ISBNReserva) && libro.getDisponible()) {
                            libroReserva = libro;
                            break;
                        }
                    }

                    if (libroReserva != null) {
                        reservarLibro(libroReserva, biblioteca);
                    } else {
                        System.out.println("El libro no está disponible o no existe.");
                    }
                    break;
                case 2:
                    System.out.println("Introduce el ISBN del libro que deseas solicitar:");
                    String ISBN = teclado.next();
                    teclado.nextLine();
                    System.out.println("Introduce el título del libro:");
                    String titulo = teclado.nextLine();
                    System.out.println("Introduce el autor del libro:");
                    String autor = teclado.nextLine();
                    this.solicitarLibro(ISBN, titulo, autor, biblioteca);
                    break;
                case 3:
                    // Devolver libro
                    System.out.println("Introduce el ISBN del libro que deseas devolver: ");
                    String ISBNDevolucion = teclado.next();

                    Libro libroDevolucion = null;
                    for (Libro libro : librosReservados) {
                        if (libro.getISBN().equals(ISBNDevolucion)) {
                            libroDevolucion = libro;
                            break;
                        }
                    }

                    if (libroDevolucion != null) {
                        devolverLibro(libroDevolucion, biblioteca);
                    } else {
                        System.out.println("No has reservado este libro.");
                    }
                    break;

                case 4:
                    System.out.print("Introduce tu contraseña actual: ");
                    String passwordActual = teclado.next();
                    if (ComprobarPassword(passwordActual)) {
                        System.out.print("Introduce tu nueva contraseña: ");
                        String passwordNueva = teclado.next();
                        setPassword(passwordNueva);
                        System.out.println("Contraseña cambiada correctamente");
                    } else {
                        System.out.println("Contraseña incorrecta");
                    }

                    System.out.println("Desea cambiar la pista de contraseña? (S/N)");
                    String respuesta = teclado.next();
                    if (respuesta.equals("S")) {
                        System.out.print("Introduce tu nueva pista de contraseña: ");
                        String pistaPasswordNueva = teclado.next();
                        setPistaPassword(pistaPasswordNueva);
                        System.out.println("Pista de contraseña cambiada correctamente");
                    }
                    break;

                case 5:
                    System.out.println("Cerrando sesión...");
                    salir = true; // Salir del menú de usuario
                    break;
                default:
                    System.out.println("Opción no válida");
                    break;
            }
        }
    }
}
