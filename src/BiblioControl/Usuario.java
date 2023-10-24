package BiblioControl;

import java.util.ArrayList;
import java.util.Scanner;

public class Usuario {
    private String DNI;
    private String nombre;
    private String password;
    private String pistaPassword;
    private ArrayList<Libro> librosReservados = new ArrayList<>();

    public Usuario(String DNI, String nombre, String password, String pistaPassword) {
        this.DNI = DNI;
        this.nombre = nombre;
        this.password = password;
        this.pistaPassword = pistaPassword;
    }

    public String getDNI() {
        return DNI;
    }

    public String getNombre() {
        return nombre;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPistaPassword(String pistaPassword) {
        this.pistaPassword = pistaPassword;
    }

    public String getPistaPassword() {
        return this.pistaPassword;
    }

    public static String getNombre(String DNI, ArrayList<Usuario> Usuarios) {
        String nombre = "";
        for (Usuario usuario : Usuarios) {
            if (usuario.getDNI().equals(DNI)) {
                nombre = usuario.getNombre();
            }
        }
        return nombre;
    }

    // Metodo para reservar un libro
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

    public boolean ComprobarPassword(String password) {
        return this.password.equals(password);
    }

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

    public void MenuUsuario(ArrayList<Libro> biblioteca) {
        boolean salir = false;
        Scanner teclado = new Scanner(System.in);
        int opcion;

        while (!salir) {
            System.out.println("\nMENU USUARIO");
            System.out.println("1. Comprobar disponibilidad");
            System.out.println("2. Reservar libro");
            System.out.println("3. Solicitar libro");
            System.out.println("4. Devolver libro");
            System.out.println("5. Cambiar contraseña");
            System.out.println("6. Salir");
            System.out.print("Escribe una de las opciones: ");
            opcion = teclado.nextInt();

            switch (opcion) {
                case 1:
                    // Comprobar disponibilidad
                    break;
                case 2:
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
                case 3:
                    // Solicitar libro
                    break;
                case 4:
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

                case 5:
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

                case 6:
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
