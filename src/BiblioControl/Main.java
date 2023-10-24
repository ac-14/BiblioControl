package BiblioControl;

import java.util.Scanner;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList<Usuario> Usuarios = new ArrayList<Usuario>();
        ArrayList<Libro> Libros = new ArrayList<Libro>();

        // Usuarios de prueba
        Usuario U1 = new Usuario("12345678A", "Pepe", "1234", "1234");
        Usuario U2 = new Usuario("87654321B", "Juan", "4321", "4321");
        Usuario U3 = new Usuario("11111111C", "Maria", "1111", "1111");
        Usuarios.add(U1);
        Usuarios.add(U2);
        Usuarios.add(U3);

        // Libros de prueba
        Libro L1 = new Libro("1234567890", "El Quijote", "Cervantes");
        Libro L2 = new Libro("0987654321", "El Señor de los Anillos", "Tolkien");
        Libros.add(L1);
        Libros.add(L2);


        // Crear un objeto Admin
        Admin admin = new Admin("admin", "adminpassword");

        boolean salir = false;
        while (!salir) {
            // Menú Inicial: Iniciar Sesion, Crear Usuario y Salir
            System.out.println("Bienvenido a BiblioControl");
            System.out.println("1. Iniciar Sesion");
            System.out.println("2. Crear Usuario");
            System.out.println("3. Salir");


            Scanner teclado = new Scanner(System.in);
            int opcion = teclado.nextInt();

            switch (opcion) {
                case 1:
                    // Iniciar Sesion
                    System.out.println("Introduce tu DNI");
                    String DNI = teclado.next();

                    Usuario UsuarioTemp = null;
                    for (Usuario usuario : Usuarios) {
                        if (usuario.getDNI().equals(DNI)) {
                            UsuarioTemp = usuario;
                            break;
                        }
                    }
                    if (DNI.equals("admin")) {
                        System.out.println("Introduce tu contraseña");
                        String password = teclado.next();

                        if (password.equals("adminpassword")) {
                            // Invoca el menú de administrador
                            Admin.MenuAdmin(Usuarios, Libros);
                            continue;
                        } else {
                            System.out.println("Contraseña de administrador incorrecta.");
                            break;
                        }
                    }
                    
                    if (UsuarioTemp != null) {
                        boolean autenticado = false;

                        do {
                            System.out.println("Introduce tu contraseña");
                            String password = teclado.next();

                            if (UsuarioTemp.ComprobarPassword(password)) {
                                // Si la contraseña es correcta, UsuarioTemp representa al usuario autenticado
                                System.out.println("Bienvenido " + UsuarioTemp.getNombre());
                                UsuarioTemp.MenuUsuario(Libros);
                                autenticado = true;
                            } else {
                                // Si la contraseña es incorrecta, mostrar mensaje de error y pista
                                System.out.println("Contraseña incorrecta");
                                System.out.println("Pista: " + UsuarioTemp.getPistaPassword());
                            }
                        } while (!autenticado);
                    } else {
                        // Si no existe, mostrar mensaje de error
                        System.out.println("El usuario no existe");
                    }
                    break;

                case 2:
                    // Crear Usuario
                    Admin.AddUsuario(Usuarios);
                    break;

                case 3:
                    // Salir
                    System.out.println("Gracias por usar BiblioControl");
                    salir = true;
                    break;

                default:
                    System.out.println("Opción no válida");
                    break;
            }
        }
    }
}
