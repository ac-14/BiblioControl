package BiblioControl;

import java.util.ArrayList;
import java.util.Scanner;

public class Usuario {
    private String DNI;
    private String nombre;
    private String password;
    private String pistaPassword;

    // Constructor
    public Usuario(String DNI, String nombre, String password, String pistaPassword){
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


    public void setPassword(String password){
        this.password = password;
    }

    public String getPassword(){
        return password;
    }

    public void setPistaPassword(String pistaPassword){
        this.pistaPassword = pistaPassword;
    }

    public String getPistaPassword(){
        return this.pistaPassword;
    }

    // Metodo para obtener el nombre del usuario a partir de su DNI
    public static String getNombre(String DNI, ArrayList<Usuario> Usuarios){
        String nombre = "";
        for(int i = 0; i < Usuarios.size(); i++){
            if(Usuarios.get(i).getDNI().equals(DNI)){
                nombre = Usuarios.get(i).nombre;
            }
        }
        return nombre;
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

    // Menu Usuario : Comprobar disponibilidad, reservar libro, solicitar libro, devolver libro, pista contraseña
    public static void MenuUsuario(Usuario usuario) {
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
                    break;
                case 3:
                    // Solicitar libro
                    break;
                case 4:
                    // Devolver libro
                    break;

                case 5:
                    System.out.print("Introduce tu contraseña actual: ");
                    String passwordActual = teclado.next();
                    if (usuario.ComprobarPassword(passwordActual)) {
                        System.out.print("Introduce tu nueva contraseña: ");
                        String passwordNueva = teclado.next();
                        usuario.cambiarPassword(passwordNueva);
                        System.out.println("Contraseña cambiada correctamente");
                    } else {
                        System.out.println("Contraseña incorrecta");
                    }

                    System.out.println("Desea cambiar la pista de contraseña? (S/N)");
                    String respuesta = teclado.next();
                    if (respuesta.equals("S")) {
                        System.out.print("Introduce tu nueva pista de contraseña: ");
                        String pistaPasswordNueva = teclado.next();
                        usuario.setPistaPassword(pistaPasswordNueva);
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

    public boolean ComprobarUsuario(ArrayList<Usuario> Usuarios) {
        for (Usuario usuario : Usuarios) {
            if (this.getDNI().equals(usuario.getDNI())) {
                return true; // El usuario existe
            }
        }
        return false; // El usuario no existe
    }

    public boolean ComprobarPassword(String password) {
        return this.password.equals(password);
    }

    public void cambiarPassword(String password){
        this.password = password;
    }
    
}