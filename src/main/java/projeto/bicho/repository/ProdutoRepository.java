package projeto.bicho.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import projeto.bicho.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{
	
	@Query("SELECT p FROM Produto p WHERE p.animal.id = :animalId")
    List<Produto> findByAnimal(@Param("animalId") Long animalId);
    
    @Query("SELECT p FROM Produto p WHERE p.animal.id = :animalId AND p.categoria.id = :categoriaId")
    List<Produto> findByCategoria(@Param("animalId") Long animalId, @Param("categoriaId") Long categoriaId);
    
    @Query("SELECT p FROM Produto p WHERE p.animal.id = :animalId AND p.subcategoria.id = :subcategoriaId")
    List<Produto> findBySubcategoria(@Param("animalId") Long animalId, @Param("subcategoriaId") Long subcategoriaId);
    
    List<Produto> findByNomeContainingIgnoreCaseOrDescricaoContainingIgnoreCase(String nome, String descricao);
    
    @Query(value = "SELECT * FROM produto p ORDER BY p.id DESC LIMIT 5", nativeQuery = true)
    List<Produto> findNovidades();
    
    @Query(value = "SELECT * FROM produto p WHERE p.animal_id = 1 ORDER BY RAND(p.id) LIMIT 5", nativeQuery = true)
    List<Produto> findCachorroIndex();
    
    @Query(value = "SELECT * FROM produto p WHERE p.animal_id = 2 ORDER BY RAND(p.id) LIMIT 5", nativeQuery = true)
    List<Produto> findGatoIndex();
    
    @Query(value = "SELECT * FROM produto p WHERE p.animal_id = 3 ORDER BY RAND(p.id) LIMIT 5", nativeQuery = true)
    List<Produto> findOutrosIndex();
}
