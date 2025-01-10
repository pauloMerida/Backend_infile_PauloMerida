package noticias.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Noticia {
    @Id
    @Column(name = "Id_noticia", columnDefinition = "INT")
    private Integer Id_noticia;
    private String titulo;
    private String cuerpo;
    private String fechaPublicacion;
    private String fotoNoticia;
    private Integer idAutor;
    private String nombre;
    private String foto;
    private Integer idCategoria;
    private String categoria;

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(Integer idAutor) {
        this.idAutor = idAutor;
    }

    public String getFotoNoticia() {
        return fotoNoticia;
    }

    public void setFotoNoticia(String fotoNoticia) {
        this.fotoNoticia = fotoNoticia;
    }

    public String getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(String fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getId_noticia() {
        return Id_noticia;
    }

    public void setId_noticia(Integer id_noticia) {
        Id_noticia = id_noticia;
    }
}