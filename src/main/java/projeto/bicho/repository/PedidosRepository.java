package projeto.bicho.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import projeto.bicho.model.Pedidos;

@Repository
public interface PedidosRepository extends JpaRepository<Pedidos, Long>{

	@Query("SELECT p FROM Pedidos p WHERE p.userId = :userId")
	List<Pedidos> findByUserId(@Param("userId") Long userId);
}
