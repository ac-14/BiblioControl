package BiblioControl;

import java.util.ArrayList;
import java.util.Scanner;

// Bibliotecas para el sonido
import javax.sound.sampled.*;

/**
 * Clase Admin que hereda de Usuario.
 * Contiene los metodos para añadir y eliminar usuarios y libros, y para gestionar el sonido de la sala y las peticiones.
 */
    public class Admin extends Usuario {


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
     * Metodo para añadir usuarios al ArrayList Usuarios
     * Pide los datos del usuario y crea un nuevo usuario
     * @param Usuarios ArrayList de usuarios
     */
    public static void AddUsuario(ArrayList<UsuarioBiblioteca> Usuarios){
        Scanner teclado = new Scanner(System.in);

        System.out.println("Introduce tu DNI");
        String DNI = teclado.nextLine();

        // Utilizar el método validarDNI para comprobar la validez del DNI introducido.
        while (!UsuarioBiblioteca.validarDNI(DNI)) {
            System.out.println("El DNI introducido no es válido. Por favor, vuelve a introducir tu DNI.");
            DNI = teclado.nextLine();
        }

        System.out.println("Introduce tu nombre");
        String nombre = teclado.nextLine();
        System.out.println("Introduce tu contraseña");
        String password = teclado.nextLine();
        System.out.println("Introduce una pista para tu contraseña");
        String pistaPassword = teclado.nextLine();

        // Crear un nuevo usuario y agregarlo a la lista de Usuarios
        UsuarioBiblioteca nuevoUsuario = new UsuarioBiblioteca(DNI, nombre, password, pistaPassword);
        Usuarios.add(nuevoUsuario);
        System.out.println("Usuario creado.");
    }

    /**
     * Metodo para eliminar usuarios del ArrayList Usuarios
     * Busca el usuario por su DNI y lo elimina
     * @param DNI DNI del usuario
     * @param Usuarios ArrayList de usuarios
     */
    public static void DelUsuario(String DNI, ArrayList<UsuarioBiblioteca> Usuarios){
        for(int i = 0; i < Usuarios.size(); i++){
            if(Usuarios.get(i).getDNI().equals(DNI)){
                Usuarios.remove(i);
            }
        }
    }

    /**
     * Metodo para añadir libros al ArrayList Libros
     * Pide los datos del libro y crea un nuevo libro y lo añade al ArrayList
     * @param ISBN   ISBN del libro
     * @param titulo Titulo del libro
     * @param autor  Autor del libro
     * @param Libros ArrayList de libros
     */
    public static void addLibros(String ISBN, String titulo, String autor, ArrayList<Libro> Libros){
        Libro libro = new Libro(ISBN, titulo, autor);
        libro.setISBN(ISBN);
        libro.setTitulo(titulo);
        libro.setAutor(autor);
        Libros.add(libro);
    }

    /**
     * Metodo para eliminar libros del ArrayList Libros
     * Busca el libro por su ISBN y lo elimina con el metodo remove de ArrayList
     * @param ISBN ISBN del libro
     * @param Libros ArrayList de libros
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
    public static boolean autenticarAdmin(Admin admin, String password) {
        if (admin.getDNI().equals("admin") && admin.ComprobarPassword(password)) {
            return true;
        }
        return false;
    }

    /**
     * Excepcion para cuando no hay peticiones
     */
    public static class SinPeticionesException extends Exception {
        public SinPeticionesException(String mensaje) {
            super(mensaje);
        }
    }

    /**
     * Metodo para añadir peticiones de libros
     * @param libro Libro que se va a añadir a las peticiones
     */
    public static void addPeticion(Libro libro) {
        peticiones.add(libro);
        System.out.println("Petición añadida: " + libro.getTitulo() + " (ISBN: " + libro.getISBN() + ")");
    }

    /**
     * Metodo para mostrar las peticiones
     * Recorre el ArrayList peticiones y muestra las peticiones
     */
    public static void mostrarPeticiones() throws SinPeticionesException {
        if (peticiones.isEmpty()) {
            throw new SinPeticionesException("No hay peticiones para mostrar.");
        }
        for (int i = 0; i < peticiones.size(); i++) {
            Libro libro = peticiones.get(i);
            System.out.println((i + 1) + ". " + libro.getTitulo() + " (ISBN: " + libro.getISBN() + ")");
        }
    }

    /**
     * Metodo para eliminar peticiones
     */
    private static int obtenerIndicePeticion() {
        Scanner teclado = new Scanner(System.in);
        try {
            mostrarPeticiones();
            System.out.println("Escribe el número de la petición que quieres gestionar:");
            return teclado.nextInt() - 1;
        } catch (SinPeticionesException e) {
            System.out.println(e.getMessage());
            return -1; // Indica que no hay peticiones
        }
    }

    /**
     * Metodo para eliminar peticiones
     * @param index Indice de la petición que se va a eliminar
     */
    public static void eliminarPeticion(int index) {
        if (index >= 0 && index < peticiones.size()) {
            peticiones.remove(index);
            System.out.println("Petición eliminada.");
        } else {
            System.out.println("Índice de petición no válido.");
        }
    }

    /**
     * Metodo para obtener el indice de la peticion
     * @param indicePeticion Indice de la peticion
     * @param Libros ArrayList de libros
     */
    private static void gestionarAddLibro(int indicePeticion, ArrayList<Libro> Libros) {
        if (indicePeticion >= 0 && indicePeticion < peticiones.size()) {
            Libro libroAAñadir = peticiones.get(indicePeticion);
            addLibros(libroAAñadir.getISBN(), libroAAñadir.getTitulo(), libroAAñadir.getAutor(), Libros);
            eliminarPeticion(indicePeticion);
        } else {
            System.out.println("Índice de petición no válido.");
        }
    }

    /**
     * Metodo para medir el nivel de sonido de la sala
     */
    public static void medirNivelDeSonido() {
        try {

            AudioFormat format = new AudioFormat(44100, 16, 1, true, true);
            DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
            if (!AudioSystem.isLineSupported(info)) {
                System.out.println("Micrófono no soportado");
                return;
            }
            // Abrir el micrófono y comenzar a grabar
            TargetDataLine line = (TargetDataLine) AudioSystem.getLine(info);
            line.open(format);
            line.start();

            // Solicitar segundos de medición
            System.out.print("Durante cuántos segundos quieres medir el nivel de sonido? ");
            Scanner teclado = new Scanner(System.in);
            int segundos = teclado.nextInt();
            int milisegundos = segundos * 1000;

            // Leer datos del micrófono y calcular el nivel de sonido
            byte[] buffer = new byte[1024];
            // Se utiliza System.currentTimeMillis() para obtener el tiempo actual en milisegundos y se le suma la duración de la medición
            long endTime = System.currentTimeMillis() + milisegundos;
            System.out.println("Iniciando medición de sonido por " + segundos + " segundos...");

            // Mientras el tiempo actual sea menor que el tiempo final, leer datos del micrófono y calcular el nivel de sonido
            while (System.currentTimeMillis() < endTime) {
                int bytesRead = line.read(buffer, 0, buffer.length);
                // Calcular el nivel de sonido en decibelios
                double level = calculateLevel(buffer, bytesRead);
                System.out.print("\rNivel de sonido (dB): " + level);
            }
            line.stop();
            line.close();
            System.out.println("\nMedición de sonido completada.");

        } catch (Exception e) {
            e.printStackTrace();
        }
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
     * Metodo que muestra el menu de administrador
     * Desde aqui se pueden añadir y eliminar usuarios y libros, gestionar el sonido de la sala y las peticiones
     * @param Usuarios ArrayList de usuarios de la biblioteca
     * @param Libros ArrayList de libros de la biblioteca
     */
    public void Menu(ArrayList<UsuarioBiblioteca> Usuarios, ArrayList<Libro> Libros){
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
            teclado.nextLine();

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
                    String ISBN = teclado.nextLine();
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
                    medirNivelDeSonido();
                    break;
                case 6:
                    int indicePeticion = obtenerIndicePeticion();
                    // Verifica si hay peticiones disponibles
                    if (indicePeticion == -1) {
                        break;
                    }
                    System.out.println("¿Quieres añadir el libro a la biblioteca o eliminar la petición? (añadir/eliminar):");
                    String accion = teclado.next();

                    if ("añadir".equalsIgnoreCase(accion)) {
                        gestionarAddLibro(indicePeticion, Libros);
                    } else if ("eliminar".equalsIgnoreCase(accion)) {
                        eliminarPeticion(indicePeticion);
                    } else {
                        System.out.println("Acción no reconocida.");
                    }
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