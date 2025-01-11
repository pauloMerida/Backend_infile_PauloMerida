package noticias;


import noticias.Models.Categoria;
import noticias.Repository.CategoriaRepository;
import noticias.Services.CategoriaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

public class CategoriaServiceTest {

    @Mock
    private CategoriaRepository categoriaRepository;

    @InjectMocks
    private CategoriaService categoriaService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetCategorias() {
        // Crear datos simulados para la prueba
        Categoria categoria1 = new Categoria();
        categoria1.setId(1);
        categoria1.setCategoria("Deportes");

        Categoria categoria2 = new Categoria();
        categoria2.setId(2);
        categoria2.setCategoria("Tecnología");

        // Simulando la consulta del repositorio
        when(categoriaRepository.findAllCategorias()).thenReturn(Arrays.asList(categoria1, categoria2));

        // Llamar al método a probar
        List<Categoria> categorias = categoriaService.getCategorias();

        // Verificar que el resultado sea el esperado
        assertNotNull(categorias);
        assertEquals(2, categorias.size());
        assertEquals("Deportes", categorias.get(0).getCategoria());
        assertEquals("Tecnología", categorias.get(1).getCategoria());

        // Verificar que el repositorio fue llamado correctamente
        verify(categoriaRepository, times(1)).findAllCategorias();
    }

}
