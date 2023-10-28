package BiblioControl;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Clase Admin que hereda de Usuario.
 * Contiene los metodos para añadir y eliminar usuarios y libros, y para gestionar el sonido de la sala y las peticiones.
 */
public class Admin extends Usuario{

    /**
     * Constructor
     * @param DNI DNI del administrador
     * @param password Contraseña del administrador
     */
    public Admin(String DNI, String password){
        super(DNI, "", password, "");
    }

    /**
     * Metodo para añadir usuarios al ArrayList Usuarios
     * Pide los datos del usuario y crea un nuevo usuario
     * @param Usuarios ArrayList de usuarios
     */
    public static void AddUsuario(ArrayList<Usuario> Usuarios){
        Scanner teclado = new Scanner(System.in);

        System.out.println("Introduce tu DNI");
        String DNI = teclado.nextLine();
        System.out.println("Introduce tu nombre");
        String nombre = teclado.nextLine();
        System.out.println("Introduce tu contraseña");
        String password = teclado.nextLine();
        System.out.println("Introduce una pista para tu contraseña");
        String pistaPassword = teclado.nextLine();

        // Crear un nuevo usuario y agregarlo a la lista de Usuarios
        Usuario nuevoUsuario = new Usuario(DNI, nombre, password, pistaPassword);
        Usuarios.add(nuevoUsuario);
        System.out.println("Usuario creado.");
    }


    /**
     * Metodo para eliminar usuarios del ArrayList Usuarios
     * Busca el usuario por su DNI y lo elimina
     * @param DNI DNI del usuario
     * @param Usuarios ArrayList de usuarios
     */
    public static void DelUsuario(String DNI, ArrayList<Usuario> Usuarios){
        for(int i = 0; i < Usuarios.size(); i++){
            if(Usuarios.get(i).getDNI().equals(DNI)){
                Usuarios.remove(i);
            }
        }
    }

    /**
     * Metodo para añadir libros al ArrayList Libros
     * Pide los datos del libro y crea un nuevo libro y lo añade al ArrayList
     * @param ISBN ISBN del libro
     * @param titulo Titulo del libro
     * @param autor Autor del libro
     * @param Libros ArrayList de libros
     * @return devuelve el ArrayList de libros actualizado
     */
    public static ArrayList<Libro> addLibros(String ISBN, String titulo, String autor, ArrayList<Libro> Libros){
        Libro libro = new Libro(ISBN, titulo, autor);
        libro.setISBN(ISBN);
        libro.setTitulo(titulo);
        libro.setAutor(autor);
        Libros.add(libro);
        return Libros;
    }

    /**
     * Metodo para eliminar libros del ArrayList Libros
     * Busca el libro por su ISBN y lo elimina con el metodo remove de ArrayList
     * @param ISBN
     * @param Libros
     */
    public static void delLibros(String ISBN, ArrayList<Libro> Libros){
        for(int i = 0; i < Libros.size(); i++){
            if(Libros.get(i).getISBN().equals(ISBN)){
                Libros.remove(i);
            }
        }
    }

    /**
     * Metodo autenticarAdmin
     * Comprueba si el DNI y la contraseña introducidos son correctos
     * @param DNI Nombre del administrador
     * @param password Contraseña del administrador
     * @return devuelve true si el usuario y la contraseña son correctos, false si no lo son
     */
    public static boolean autenticarAdmin(String DNI, String password) {
        if (DNI.equals("admin") && password.equals("adminpassword")) {
            return true;
        }
        return false;
    }

    // METODO SONIDO SALA

    // METODO GESTIONAR PETICIONES


    /**
     * Metodo que muestra el menu de administrador
     * Desde aqui se pueden añadir y eliminar usuarios y libros, gestionar el sonido de la sala y las peticiones
     * @param Usuarios ArrayList de usuarios de la biblioteca
     * @param Libros ArrayList de libros de la biblioteca
     */
    public static void MenuAdmin(ArrayList<Usuario> Usuarios, ArrayList<Libro> Libros){
        Admin admin = new Admin("admin", "adminpassword");
        boolean salir = false;
        Scanner teclado = new Scanner(System.in);
        int opcion;

        while (!salir) {
            System.out.println("\nMENU ADMIN");
            System.out.println("1. Añadir usuario");
            System.out.println("2. Eliminar usuario");
            System.out.println("3. Añadir libro");
            System.out.println("4. Eliminar libro");
            System.out.println("5. Sonido sala");
            System.out.println("6. Gestionar peticiones");
            System.out.println("7. Salir");

            opcion = teclado.nextInt();

            switch (opcion) {
                case 1:
                    AddUsuario(Usuarios);
                    break;

                case 2:
                    System.out.println("Introduce el DNI del usuario que quieres eliminar");
                    String DNI = teclado.next();
                    System.out.println("Introduce la contraseña del administrador");
                    String password = teclado.next();
                    if(admin.ComprobarPassword(password)){
                        DelUsuario(DNI, Usuarios);
                        System.out.println("Usuario eliminado");
                    }else{
                        System.out.println("Contraseña del administrador incorrecta");
                    }

                    break;

                case 3:
                    System.out.println("Introduce el ISBN del libro");
                    String ISBN = teclado.next();
                    System.out.println("Introduce el titulo del libro");
                    String titulo = teclado.nextLine();
                    System.out.println("Introduce el autor del libro");
                    String autor = teclado.nextLine();
                    addLibros(ISBN, titulo, autor, Libros);
                    break;

                case 4:
                    System.out.print("Introduce el ISBN del libro que quieres eliminar: ");
                    ISBN = teclado.next();
                    delLibros(ISBN, Libros);
                    break;

                case 5:
                    // METODO SONIDO SALA
                    break;

                case 6:

                    break;

                case 7:
                    System.out.println("Cerrando sesión...");
                    salir = true;
                    break;

                default:
                    System.out.println("Solo números entre 1 y 7");
            }
        }
    }
}
