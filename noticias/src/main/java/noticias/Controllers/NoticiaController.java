package noticias.Controllers;

import noticias.Models.Noticia;
import noticias.Services.NoticiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/noticias")
public class NoticiaController {
    @Autowired
    private NoticiaService noticiaService;

    @GetMapping("/mostrar")
    public List<Noticia> mostrarNoticias() {
        return noticiaService.mostrarNoticias();
    }

    @GetMapping("/categoria/{categoriaId}")
    public List<Noticia> obtenerNoticiasPorCategoria(@PathVariable int categoriaId) {
        return noticiaService.mostrarNoticiasPorCategoria(categoriaId);
    }
}
