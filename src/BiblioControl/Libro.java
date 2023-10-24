package BiblioControl;

public class Libro {
    private String ISBN;
    private String titulo;
    private String autor;
    private boolean disponible;

    // Constructor
    public Libro(String ISBN, String titulo, String autor){
        this.ISBN = ISBN;
        this.titulo = titulo;
        this.autor = autor;
        this.disponible = true;
    }

    public String getISBN(){
        return ISBN;
    }

    public void setISBN(String ISBN){
        this.ISBN = ISBN;
    }

    public String getTitulo(){
        return titulo;
    }

    public void setTitulo(String titulo){
        this.titulo = titulo;
    }

    public String getAutor(){
        return autor;
    }

    public void setAutor(String autor){
        this.autor = autor;
    }

    public boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

}
