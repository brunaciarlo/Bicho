package projeto.bicho.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import projeto.bicho.model.Favoritos;

@Repository
public interface FavoritosRepository extends JpaRepository<Favoritos, Long>{
	
	@Query("SELECT f FROM Favoritos f WHERE f.userId = :userId AND f.produtoId = :produtoId")
	Favoritos findByUserIdAndProdutoId(Long userId, Long produtoId);
	
	@Query("SELECT f FROM Favoritos f WHERE f.userId = :userId")
	List<Favoritos> findByUserId(@Param("userId") Long userId);
	
	@Query("SELECT f FROM Favoritos f WHERE f.userId = :userId")
	List<Favoritos> findAllByUserId(Long userId);
}
