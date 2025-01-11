package noticias;

import noticias.Models.Noticia;
import noticias.Services.NoticiaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class NoticiaServiceTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private NoticiaService noticiaService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testMostrarNoticias() {
        // Crear datos simulados para la prueba
        Noticia noticia1 = new Noticia();
        noticia1.setId_noticia(1);
        noticia1.setTitulo("Noticia 1");
        noticia1.setCuerpo("Contenido de la noticia 1");
        noticia1.setFechaPublicacion("2025-01-01");
        noticia1.setFotoNoticia("foto1.jpg");
        noticia1.setIdAutor(1);
        noticia1.setNombre("Autor 1");
        noticia1.setFoto("fotoAutor1.jpg");
        noticia1.setIdCategoria(1);
        noticia1.setCategoria("Categoría 1");

        Noticia noticia2 = new Noticia();
        noticia2.setId_noticia(2);
        noticia2.setTitulo("Noticia 2");
        noticia2.setCuerpo("Contenido de la noticia 2");
        noticia2.setFechaPublicacion("2025-01-02");
        noticia2.setFotoNoticia("foto2.jpg");
        noticia2.setIdAutor(2);
        noticia2.setNombre("Autor 2");
        noticia2.setFoto("fotoAutor2.jpg");
        noticia2.setIdCategoria(2);
        noticia2.setCategoria("Categoría 2");

        // Crear un RowMapper que devuelva las noticias simuladas
        when(jdbcTemplate.query(eq("EXEC ObtenerNoticiasConDetalles"), any(RowMapper.class)))
                .thenAnswer(invocation -> {
                    RowMapper<?> rowMapper = invocation.getArgument(1); // Obtener el RowMapper
                    return Arrays.asList(noticia1, noticia2); // Simular la lista de noticias
                });

        // Llamar al método a probar
        List<Noticia> noticias = noticiaService.mostrarNoticias();

        // Verificar que el resultado sea el esperado
        assertNotNull(noticias);
        assertEquals(2, noticias.size());
        assertEquals("Noticia 1", noticias.get(0).getTitulo());
        assertEquals("Noticia 2", noticias.get(1).getTitulo());

        // Verificar que el JdbcTemplate fue llamado correctamente
        verify(jdbcTemplate, times(1)).query(eq("EXEC ObtenerNoticiasConDetalles"), any(RowMapper.class));
    }

    @Test
    public void testMostrarNoticiasPorCategoria() {
        // Crear datos simulados para la prueba
        Noticia noticia1 = new Noticia();
        noticia1.setId_noticia(1);
        noticia1.setTitulo("Noticia 1");
        noticia1.setCuerpo("Contenido de la noticia 1");
        noticia1.setFechaPublicacion("2025-01-01");
        noticia1.setFotoNoticia("foto1.jpg");
        noticia1.setIdAutor(1);
        noticia1.setNombre("Autor 1");
        noticia1.setFoto("fotoAutor1.jpg");
        noticia1.setIdCategoria(1);
        noticia1.setCategoria("Categoría 1");

        Noticia noticia2 = new Noticia();
        noticia2.setId_noticia(2);
        noticia2.setTitulo("Noticia 2");
        noticia2.setCuerpo("Contenido de la noticia 2");
        noticia2.setFechaPublicacion("2025-01-02");
        noticia2.setFotoNoticia("foto2.jpg");
        noticia2.setIdAutor(2);
        noticia2.setNombre("Autor 2");
        noticia2.setFoto("fotoAutor2.jpg");
        noticia2.setIdCategoria(1);
        noticia2.setCategoria("Categoría 1");

        // Simulando la ejecución del método query para filtrar por categoría
        when(jdbcTemplate.query(eq("EXEC ObtenerNoticiasPorCategoria @CategoriaId = ?"), eq(new Object[]{1}), any(RowMapper.class)))
                .thenAnswer(invocation -> {
                    RowMapper<?> rowMapper = invocation.getArgument(2); // Obtener el RowMapper
                    return Arrays.asList(noticia1, noticia2); // Simular las noticias filtradas por categoría
                });

        // Llamar al método a probar
        List<Noticia> noticiasPorCategoria = noticiaService.mostrarNoticiasPorCategoria(1);

        // Verificar que el resultado sea el esperado
        assertNotNull(noticiasPorCategoria);
        assertEquals(2, noticiasPorCategoria.size());
        assertEquals("Noticia 1", noticiasPorCategoria.get(0).getTitulo());
        assertEquals("Noticia 2", noticiasPorCategoria.get(1).getTitulo());

        // Verificar que el JdbcTemplate fue llamado correctamente con los parámetros adecuados
        verify(jdbcTemplate, times(1)).query(eq("EXEC ObtenerNoticiasPorCategoria @CategoriaId = ?"), eq(new Object[]{1}), any(RowMapper.class));
    }

}
