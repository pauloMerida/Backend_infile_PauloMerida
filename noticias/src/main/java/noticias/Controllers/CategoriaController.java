package noticias.Controllers;

import noticias.Models.Categoria;
import noticias.Services.CategoriaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping("/categorias")
    public List<Categoria> getCategorias() {
        return categoriaService.getCategorias();
    }
}
