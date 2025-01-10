package noticias.Services;

import noticias.Models.Noticia;
import noticias.Repository.NoticiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class NoticiaService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Noticia> mostrarNoticias() {
        String sql = "EXEC ObtenerNoticiasConDetalles";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Noticia noticia = new Noticia();
            noticia.setId_noticia(rs.getInt("Id_noticia"));
            noticia.setTitulo(rs.getString("Titulo"));
            noticia.setCuerpo(rs.getString("Cuerpo"));
            noticia.setFechaPublicacion(rs.getString("FechaPublicacion"));
            noticia.setFotoNoticia(rs.getString("FotoNoticia"));
            noticia.setIdAutor(rs.getInt("IdAutor"));
            noticia.setIdCategoria(rs.getInt("IdCategoria"));
            return noticia;
        });
    }

    public List<Noticia> mostrarNoticiasPorCategoria(int categoriaId) {

        String sql = "EXEC ObtenerNoticiasPorCategoria @CategoriaId = ?";

        return jdbcTemplate.query(sql, new Object[]{categoriaId}, (rs, rowNum) -> {
            Noticia noticia = new Noticia();
            noticia.setId_noticia(rs.getInt("Id_noticia"));
            noticia.setTitulo(rs.getString("Titulo"));
            noticia.setCuerpo(rs.getString("Cuerpo"));
            noticia.setFechaPublicacion(rs.getString("FechaPublicacion"));
            noticia.setFotoNoticia(rs.getString("FotoNoticia"));
            noticia.setIdAutor(rs.getInt("IdAutor"));
            noticia.setIdCategoria(rs.getInt("IdCategoria"));
            return noticia;
        });
    }
}
