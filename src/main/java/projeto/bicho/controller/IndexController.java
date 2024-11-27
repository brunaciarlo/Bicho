package projeto.bicho.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import projeto.bicho.model.Produto;
import projeto.bicho.repository.ProdutoRepository;

@Controller
public class IndexController {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@GetMapping("/")
	public String home(Model model) {
		List<Produto> produtos = produtoRepository.findNovidades();
		List<Produto> produtosCachorro = produtoRepository.findCachorroIndex();
		List<Produto> produtosGato = produtoRepository.findGatoIndex();
		List<Produto> produtosOutros = produtoRepository.findOutrosIndex();
		
		model.addAttribute("produtos", produtos);
		model.addAttribute("produtosCachorro", produtosCachorro);
		model.addAttribute("produtosGato", produtosGato);
		model.addAttribute("produtosOutros", produtosOutros);
		return "index";
	}
	
	@GetMapping("/perguntas-frequentes")
		public String perguntas() {
			return "perguntas-frequentes";
		}

}
