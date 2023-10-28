package BiblioControl;

import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    private static Scanner teclado = new Scanner(System.in);
    private static ArrayList<Usuario> Usuarios = new ArrayList<>();
    private static ArrayList<Libro> Libros = new ArrayList<>();

    public static void main(String[] args) {
        inicializarDatosPrueba();

        boolean salir = false;
        while (!salir) {
            mostrarMenuPrincipal();
            int opcion = teclado.nextInt();

            switch (opcion) {
                case 1:
                    iniciarSesion();
                    break;
                case 2:
                    Admin.AddUsuario(Usuarios);
                    break;
                case 3:
                    System.out.println("Gracias por usar BiblioControl");
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida");
                    break;
            }
        }
    }
    /**
     * Metodo inicializarDatosPrueba sirve para inicializar los datos de prueba
     *
     */
    private static void inicializarDatosPrueba() {
        Usuarios.add(new Usuario("12345678A", "Pepe", "1234", "1234"));
        Usuarios.add(new Usuario("87654321B", "Juan", "4321", "4321"));
        Usuarios.add(new Usuario("11111111C", "Maria", "1111", "1111"));
        Libros.add(new Libro("1234567890", "El Quijote", "Cervantes"));
        Libros.add(new Libro("0987654321", "El Señor de los Anillos", "Tolkien"));
    }

    /**
     * Metodo mostrarMenuPrincipal muestra las opciones del menu principal
     */
    private static void mostrarMenuPrincipal() {
        System.out.println("Bienvenido a BiblioControl");
        System.out.println("1. Iniciar Sesion");
        System.out.println("2. Crear Usuario");
        System.out.println("3. Salir");
    }

    /**
     * Metodo iniciarSesion sirve para iniciar sesion en la aplicacion
     */
    private static void iniciarSesion() {
        System.out.println("Introduce tu DNI");
        String DNI = teclado.next();

        Usuario UsuarioTemp = buscarUsuarioPorDNI(DNI);

        System.out.println("Introduce tu contraseña");
        String password = teclado.next();

        if (UsuarioTemp == null && Admin.autenticarAdmin(DNI, password)) {
            Admin.MenuAdmin(Usuarios, Libros);
        } else if (UsuarioTemp == null) {
            System.out.println("El usuario no existe o credenciales de administrador incorrectas.");
        } else {
            autenticarUsuario(UsuarioTemp, password);
        }
    }

    /**
     * Metodo buscarUsuarioPorDNI busca un usuario por su DNI
     * @param DNI
     * @return devuelve el usuario si lo encuentra, si no devuelve null
     */
    private static Usuario buscarUsuarioPorDNI(String DNI) {
        for (Usuario usuario : Usuarios) {
            if (usuario.getDNI().equals(DNI)) {
                return usuario;
            }
        }
        return null;
    }

    /**
     * Metodo autenticarUsuario sirve para verificar la contraseña del usuario
     * @param usuario es el usuario temporal que hemos creado en el metodo iniciarSesion
     * @param password es la contraseña que hemos introducido en el metodo iniciarSesion
     */
    private static void autenticarUsuario(Usuario usuario, String password) {
        boolean autenticado = false;
        do {
            if (usuario.ComprobarPassword(password)) {
                System.out.print("\nBienvenido " + usuario.getNombre());
                usuario.MenuUsuario(Libros);
                autenticado = true;
            } else {
                System.out.println("Contraseña incorrecta. Pista: " + usuario.getPistaPassword());
                System.out.println("¿Deseas intentar de nuevo? (s/n)");
                String respuesta = teclado.next().toLowerCase();
                if (respuesta.equals("n")) {
                    break;
                } else {
                    System.out.println("Introduce tu contraseña");
                    password = teclado.next();
                }
            }
        } while (!autenticado);
    }
}
