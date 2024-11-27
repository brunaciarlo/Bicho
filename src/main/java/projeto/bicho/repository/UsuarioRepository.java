package projeto.bicho.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import jakarta.transaction.Transactional;
import projeto.bicho.model.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, String>{

	Usuario findById(Long id);
	
	@Query(value = "select * from bicho_teste.usuario where email = :email and senha =:senha", nativeQuery = true)
	public Usuario login(String email, String senha);
	
	@Modifying
	@Transactional
	@Query("UPDATE Usuario u SET u.nome = :nome, u.email = :email, u.cpf = :cpf, u.data_nascimento = :data_nascimento, u.telefone = :telefone WHERE u.id = :id")
    void updateUsuario(Long id, String nome, String email, String cpf, String data_nascimento, String telefone);
	
	@Modifying
	@Transactional
	@Query("UPDATE Usuario u SET u.cep = :cep, u.endereco = :endereco, u.endereco_numero = :endereco_numero, u.estado = :estado, u.cidade = :cidade,"
			+ " u.endereco_complemento = :endereco_complemento, u.ponto_referencia = :ponto_referencia WHERE u.id = :id")
    void updateEndereco(Long id, String cep, String endereco, String endereco_numero, String estado, String cidade, String endereco_complemento, String ponto_referencia);
}
