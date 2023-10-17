package BiblioControl;

import java.util.ArrayList;
import java.util.Scanner;

public class Admin extends Usuario{

    // Constructor
    public Admin(String DNI, String password){
        super(DNI, "", password, "");
    }

    // Metodo para añadir usuarios al ArrayList Usuarios
    public static void AddUsuario(ArrayList<Usuario> Usuarios){
        Scanner teclado = new Scanner(System.in);

        System.out.println("Introduce tu DNI");
        String DNI = teclado.next();
        System.out.println("Introduce tu nombre");
        String nombre = teclado.nextLine();
        System.out.println("Introduce tu contraseña");
        String password = teclado.next();
        System.out.println("Introduce una pista para tu contraseña");
        String pistaPassword = teclado.next();

        // Crear un nuevo usuario y agregarlo a la lista de Usuarios
        Usuario nuevoUsuario = new Usuario(DNI, nombre, password, pistaPassword);
        Usuarios.add(nuevoUsuario);
        System.out.println("Usuario creado.");
    }

    // Metodo para eliminar usuarios del ArrayList Usuarios
    public void DelUsuario(String DNI, ArrayList<Usuario> Usuarios){
        for(int i = 0; i < Usuarios.size(); i++){
            if(Usuarios.get(i).getDNI().equals(DNI)){
                Usuarios.remove(i);
            }
        }
    }

// Metodo para añadir libros al ArrayList Libros
    public ArrayList<Libro> addLibros(String ISBN, String titulo, String autor, boolean disponible, ArrayList<Libro> Libros){
        Libro libro = new Libro();
        libro.setISBN(ISBN);
        libro.setTitulo(titulo);
        libro.setAutor(autor);
        libro.setDisponible(disponible);
        Libros.add(libro);
        return Libros;
    }

    // Metodo para eliminar libros del ArrayList Libros
    public void delLibros(String ISBN, ArrayList<Libro> Libros){
        for(int i = 0; i < Libros.size(); i++){
            if(Libros.get(i).getISBN().equals(ISBN)){
                Libros.remove(i);
            }
        }
    }

    // METODO SONIDO SALA

    // METODO GESTIONAR PETICIONES


    // Menu Admin : Añadir usuario, eliminar usuario, añadir libro, eliminar libro, sonido sala, gestionar peticiones, salir
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
                        admin.DelUsuario(DNI, Usuarios);
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
                    System.out.println("Introduce si el libro esta disponible");
                    boolean disponible = teclado.nextBoolean();
                    admin.addLibros(ISBN, titulo, autor, disponible, Libros);
                    break;

                case 4:
                    System.out.print("Introduce el ISBN del libro que quieres eliminar: ");
                    ISBN = teclado.next();
                    admin.delLibros(ISBN, Libros);
                    break;

                case 5:
                    // METODO SONIDO SALA
                    break;

                case 6:
                    // METODO GESTIONAR PETICIONES
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
