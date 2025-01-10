package noticias.Repository;

import noticias.Models.Categoria;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CategoriaRepository extends  CrudRepository<Categoria, Integer> {

    @Query(value = "EXEC MostrarCategorias", nativeQuery = true)
    List<Categoria> findAllCategorias();
}
