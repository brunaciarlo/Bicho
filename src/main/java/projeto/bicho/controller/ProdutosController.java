package projeto.bicho.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import projeto.bicho.model.Produto;
import projeto.bicho.repository.ProdutoRepository;

@Controller
public class ProdutosController {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@GetMapping("/cachorro/{animalId}")
	public String cachorros(@PathVariable Long animalId, Model model) {
		List<Produto> produtos = produtoRepository.findByAnimal(animalId);
		model.addAttribute("produtos", produtos);
		return "cachorro";
	}
	
	@GetMapping("/gato/{animalId}")
	public String gato(@PathVariable Long animalId, Model model) {
		List<Produto> produtos = produtoRepository.findByAnimal(animalId);
		model.addAttribute("produtos", produtos);
		return "gato";
	}
	
	@GetMapping("/outros/{animalId}")
	public String outros(@PathVariable Long animalId, Model model) {
		List<Produto> produtos = produtoRepository.findByAnimal(animalId);
		model.addAttribute("produtos", produtos);
		return "outros";
	}
	
	@GetMapping("/cachorro/{animalId}/{categoriaId}")
	public String categoriaCachorro(@PathVariable Long animalId,@PathVariable Long categoriaId, Model model) {
		List<Produto> produtos = produtoRepository.findByCategoria(animalId, categoriaId);
		model.addAttribute("produtos", produtos);
		return "cachorro";
	}
	
	@GetMapping("/gato/{animalId}/{categoriaId}")
	public String categoriaGato(@PathVariable Long animalId,@PathVariable Long categoriaId, Model model) {
		List<Produto> produtos = produtoRepository.findByCategoria(animalId, categoriaId);
		model.addAttribute("produtos", produtos);
		return "gato";
	}
	
	@GetMapping("/outros/{animalId}/{categoriaId}")
	public String categoriaOutros(@PathVariable Long animalId,@PathVariable Long categoriaId, Model model) {
		List<Produto> produtos = produtoRepository.findByCategoria(animalId, categoriaId);
		model.addAttribute("produtos", produtos);
		return "outros";
	}
	
	@GetMapping("/cachorro/{animalId}/sub/{subcategoriaId}")
	public String subcategoriaCachorro(@PathVariable Long animalId,@PathVariable Long subcategoriaId, Model model) {
		List<Produto> produtos = produtoRepository.findBySubcategoria(animalId, subcategoriaId);
		model.addAttribute("produtos", produtos);
		return "cachorro";
	}
	
	@GetMapping("/gato/{animalId}/sub/{subcategoriaId}")
	public String subcategoriaGato(@PathVariable Long animalId,@PathVariable Long subcategoriaId, Model model) {
		List<Produto> produtos = produtoRepository.findBySubcategoria(animalId, subcategoriaId);
		model.addAttribute("produtos", produtos);
		return "gato";
	}
	
	@GetMapping("/outros/{animalId}/sub/{subcategoriaId}")
	public String subcategoriaOutros(@PathVariable Long animalId,@PathVariable Long subcategoriaId, Model model) {
		List<Produto> produtos = produtoRepository.findBySubcategoria(animalId, subcategoriaId);
		model.addAttribute("produtos", produtos);
		return "outros";
	}
	
	@GetMapping("/produto/{produtoId}")
	public String produto(@PathVariable Long produtoId, Model model) {
		@SuppressWarnings("deprecation")
		Produto produto = produtoRepository.getById(produtoId);
		model.addAttribute("produto", produto);
		return "produto";
	}
	
	@GetMapping("/buscar")
	public String buscarProdutos(@RequestParam("termo") String termo, Model model) {
		List<Produto> produtos = produtoRepository.findByNomeContainingIgnoreCaseOrDescricaoContainingIgnoreCase(termo, termo);
		model.addAttribute("produtos", produtos);
		String palavraBuscada = termo.substring(0, 1).toUpperCase() + termo.substring(1).toLowerCase();
		model.addAttribute("palavraBuscada", palavraBuscada);
		return "busca";
	}
}
