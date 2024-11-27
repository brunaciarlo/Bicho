package projeto.bicho.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import projeto.bicho.model.Carrinho;

@Repository
public interface CarrinhoRepository extends JpaRepository<Carrinho, Long>{
	
	@Query("SELECT c FROM Carrinho c WHERE c.userId = :userId")
	List<Carrinho> findByUserId(@Param("userId") Long userId);
	
	@Query("SELECT c FROM Carrinho c WHERE c.userId = :userId AND c.produtoId = :produtoId")
	Carrinho findByUserIdAndProdutoId(@Param("userId") Long userId, @Param("produtoId") Long produtoId);
	
	@Query("SELECT COUNT(c) FROM Carrinho c WHERE c.userId = :userId")
    int countByUserId(@Param("userId") Long userId);
}