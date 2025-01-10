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
    private Integer idCategoria;

    public Integer getId_noticia() {
        return Id_noticia;
    }

    public void setId_noticia(Integer id_noticia) {
        Id_noticia = id_noticia;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    public String getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(String fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public String getFotoNoticia() {
        return fotoNoticia;
    }

    public void setFotoNoticia(String fotoNoticia) {
        this.fotoNoticia = fotoNoticia;
    }

    public Integer getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(Integer idAutor) {
        this.idAutor = idAutor;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }
}