package BiblioControl;

/**
 * Clase Libro
 * Contiene los atributos de un libro y sus metodos get y set
 */
public class Libro {
    private String ISBN;
    private String titulo;
    private String autor;
    private boolean disponible;

    /**
     * Constructor de la clase Libro
     * Recibe los parametros ISBN, titulo y autor y crea un nuevo libro
     * @param ISBN ISBN del libro
     * @param titulo Titulo del libro
     * @param autor Autor del libro
     */
    public Libro(String ISBN, String titulo, String autor){
        this.ISBN = ISBN;
        this.titulo = titulo;
        this.autor = autor;
        this.disponible = true;
    }

    /**
     * Metodo getISBN
     * @return devuelve el ISBN del libro
     */
    public String getISBN(){
        return ISBN;
    }

    /**
     * Metodo setISBN
     * @param ISBN ISBN del libro
     */
    public void setISBN(String ISBN){
        this.ISBN = ISBN;
    }

    /**
     * Metodo getTitulo
     * @return devuelve el titulo del libro
     */
    public String getTitulo(){
        return titulo;
    }

    /**
     * Metodo setTitulo
     * @param titulo Titulo del libro
     */
    public void setTitulo(String titulo){
        this.titulo = titulo;
    }

    /**
     * Metodo getAutor
     * @return devuelve el autor del libro
     */
    public String getAutor(){
        return autor;
    }

    /**
     * Metodo setAutor
     * @param autor Autor del libro
     */
    public void setAutor(String autor){
        this.autor = autor;
    }

    /**
     * Metodo getDisponible
     * @return devuelve si el libro esta disponible o no
     */
    public boolean getDisponible() {
        return disponible;
    }

    /**
     * Metodo setDisponible
     * @param disponible Disponibilidad del libro
     */
    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

}
