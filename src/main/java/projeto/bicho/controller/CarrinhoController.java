package projeto.bicho.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;
import projeto.bicho.model.Carrinho;
import projeto.bicho.model.Produto;
import projeto.bicho.model.Usuario;
import projeto.bicho.repository.CarrinhoRepository;
import projeto.bicho.repository.ProdutoRepository;
import projeto.bicho.repository.UsuarioRepository;
import projeto.bicho.service.CookieService;

@Controller
public class CarrinhoController {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private CarrinhoRepository carrinhoRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@GetMapping("/carrinho")
	public String carrinho(HttpServletRequest request, Model model) throws UnsupportedEncodingException {
		
		String usuarioId = CookieService.getCookie(request, "usuarioId");
		Long userId = Long.parseLong(usuarioId);
		
		List<Carrinho> itensCarrinho = carrinhoRepository.findByUserId(userId);
		List<Produto> produtos = itensCarrinho.stream()
				.map(item -> produtoRepository.findById(item.getProdutoId()).orElse(null))
				.filter(produto -> produto != null).collect(Collectors.toList());
		
		double valorTotal = produtos.stream().mapToDouble(Produto::getPreco).sum();
		
		model.addAttribute("produtos", produtos);
		model.addAttribute("valorTotal", String.format("R$ %.2f", valorTotal));
		
		return "carrinho";
	}
	
	@GetMapping("/carrinho/adicionar")
	public String adicionarAoCarrinho(HttpServletRequest request, @RequestParam Long produtoId) {
		try {
			String usuarioId = CookieService.getCookie(request, "usuarioId");
			if(usuarioId == null || usuarioId.isEmpty()) {
				return "redirect:/login";
			}
			Long userId = Long.parseLong(usuarioId);
			
			Carrinho produtoNoCarrinho = carrinhoRepository.findByUserIdAndProdutoId(userId, produtoId);
			if(produtoNoCarrinho == null) {
				Carrinho carrinho = new Carrinho();
				carrinho.setUserId(userId);
				carrinho.setProdutoId(produtoId);
				carrinhoRepository.save(carrinho);
			}
			
			return "redirect:/carrinho";
			
		} catch(Exception e) {
			e.printStackTrace();
			return "redirect:/erro";
		}
	}
	
	@PostMapping("/carrinho/remover")
	public String removerDoCarrinho(HttpServletRequest request, @RequestParam Long produtoId) {
	    try {
	        String usuarioId = CookieService.getCookie(request, "usuarioId");
	        if (usuarioId == null || usuarioId.isEmpty()) {
	            return "redirect:/login";
	        }
	        Long userId = Long.parseLong(usuarioId);

	        Carrinho produtoNoCarrinho = carrinhoRepository.findByUserIdAndProdutoId(userId, produtoId);
	        if (produtoNoCarrinho != null) {
	            carrinhoRepository.delete(produtoNoCarrinho);
	        }

	        return "redirect:/carrinho";
	    } catch (Exception e) {
	        e.printStackTrace();
	        return "redirect:/erro";
	    }
	}

	@GetMapping("/carrinho/finalizar")
	public String finalizarCompra(HttpServletRequest request, Model model) throws UnsupportedEncodingException {
		
		String usuarioId = CookieService.getCookie(request, "usuarioId");
		Long userId = Long.parseLong(usuarioId);
		Usuario usuario = usuarioRepository.findById(Long.parseLong(usuarioId));
		
		List<Carrinho> itensCarrinho = carrinhoRepository.findByUserId(userId);
		List<Produto> produtos = itensCarrinho.stream()
				.map(item -> produtoRepository.findById(item.getProdutoId()).orElse(null))
				.filter(produto -> produto != null).collect(Collectors.toList());
		
		double valorTotal = produtos.stream().mapToDouble(Produto::getPreco).sum();
		
		model.addAttribute("usuario", usuario);
		model.addAttribute("produtos", produtos);
		model.addAttribute("valorTotal", String.format("R$ %.2f", valorTotal));
		
		return "finalizar-compra";
	}
	
	@GetMapping("/carrinho/contador")
	@ResponseBody
	public int getCarrinhoContador(HttpServletRequest request) throws UnsupportedEncodingException {
	    String usuarioId = CookieService.getCookie(request, "usuarioId");
	    if (usuarioId == null || usuarioId.isEmpty()) {
	        return 0;
	    }
	    Long userId = Long.parseLong(usuarioId);
	    return carrinhoRepository.countByUserId(userId);
	}
}
