package BiblioControl;

import java.util.ArrayList;

/**
 * Clase Main
 * Contiene el metodo main
 */
public class Main {
    private static ArrayList<UsuarioBiblioteca> Usuarios = new ArrayList<>();
    private static ArrayList<Libro> Libros = new ArrayList<>();

    /**
     * Metodo main
     * @param args argumentos del metodo main
     */
    public static void main(String[] args) {
        inicializarDatosPrueba();
        new InterfazBiblioControl();
    }

    /**
     * Metodo getUsuarios
     * @return devuelve el ArrayList de usuarios
     */
    public static ArrayList<UsuarioBiblioteca> getUsuarios() {
        return Usuarios;
    }

    /**
     * Metodo getLibros
     * @return devuelve el ArrayList de libros
     */
    public static ArrayList<Libro> getLibros() {
        return Libros;
    }
    /**
     * Metodo inicializarDatosPrueba sirve para inicializar los datos de prueba
     */
    private static void inicializarDatosPrueba() {
        Usuarios.add(new UsuarioBiblioteca("12345678A", "Pepe", "1234", "1234"));
        Usuarios.add(new UsuarioBiblioteca("87654321B", "Juan", "4321", "4321"));
        Usuarios.add(new UsuarioBiblioteca("11111111C", "Maria", "1111", "1111"));
        Libros.add(new Libro("1234567890", "El Quijote", "Cervantes"));
        Libros.add(new Libro("0987654321", "El Señor de los Anillos", "Tolkien"));
        Admin.getInstance().addPeticion(new Libro("1111111111", "Libro de Aventuras", "Autor A"));
        Admin.getInstance().addPeticion(new Libro("2222222222", "Novela de Misterio", "Autor B"));
        Admin.getInstance().addPeticion(new Libro("3333333333", "Libro de Ciencia Ficción", "Autor C"));
    }

}