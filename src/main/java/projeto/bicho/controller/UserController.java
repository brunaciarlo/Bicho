package projeto.bicho.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import projeto.bicho.model.Favoritos;
import projeto.bicho.model.Pedidos;
import projeto.bicho.model.Produto;
import projeto.bicho.model.Usuario;
import projeto.bicho.repository.FavoritosRepository;
import projeto.bicho.repository.PedidosRepository;
import projeto.bicho.repository.ProdutoRepository;
import projeto.bicho.repository.UsuarioRepository;
import projeto.bicho.service.CookieService;

@Controller
public class UserController {
	
	@Autowired
	private UsuarioRepository userRepository;
	
	@Autowired
	private FavoritosRepository favoritosRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private PedidosRepository pedidosRepository;
	
	@GetMapping("/conta")
	public String conta(Model model, HttpServletRequest request) throws UnsupportedEncodingException {
		String usuarioId = CookieService.getCookie(request, "usuarioId");
		if(usuarioId != null) {
			Usuario usuario = userRepository.findById(Long.parseLong(usuarioId));
			if(usuario != null) {
				model.addAttribute("primeiroNome", usuario.getNome().split(" ")[0]);
				model.addAttribute("usuario", usuario);
			}
		}
		return "minha-conta";
	}
	
	@RequestMapping(value = "/conta", method = RequestMethod.POST)
    public String atualizaUsuario(HttpServletRequest request, HttpServletResponse response, 
                                   @RequestParam String nome, @RequestParam String email, 
                                   @RequestParam String cpf, @RequestParam String data_nascimento, 
                                   @RequestParam String telefone) throws UnsupportedEncodingException {
        
        String usuarioIdCookie = CookieService.getCookie(request, "usuarioId");
        
        if (usuarioIdCookie == null || usuarioIdCookie.isEmpty()) {
            return "redirect:/login";
        }

        Long userId = Long.parseLong(usuarioIdCookie);
        
        userRepository.updateUsuario(userId, nome, email, cpf, data_nascimento, telefone);
        CookieService.setCookie(response, "nomeUsuario", nome, 10000);

        return "redirect:/conta";
    }
	
	@GetMapping("/conta/endereco")
	public String endereco(Model model, HttpServletRequest request) throws UnsupportedEncodingException {
		String usuarioId = CookieService.getCookie(request, "usuarioId");
		if(usuarioId != null) {
			Usuario usuario = userRepository.findById(Long.parseLong(usuarioId));
			if(usuario != null) {
				model.addAttribute("primeiroNome", usuario.getNome().split(" ")[0]);
				model.addAttribute("usuario", usuario);
			}
		}
		return "meu-endereco";
	}
	
	@RequestMapping(value = "/conta/endereco", method = RequestMethod.POST)
    public String atualizaEndereco(HttpServletRequest request, @RequestParam String cep,
                                   @RequestParam String endereco, @RequestParam String endereco_numero, @RequestParam String estado, @RequestParam String cidade,
                                   @RequestParam String endereco_complemento, @RequestParam String ponto_referencia) throws UnsupportedEncodingException {
        
        String usuarioIdCookie = CookieService.getCookie(request, "usuarioId");
        
        if (usuarioIdCookie == null || usuarioIdCookie.isEmpty()) {
            return "redirect:/login";
        }

        Long userId = Long.parseLong(usuarioIdCookie);
        
        userRepository.updateEndereco(userId, cep, endereco, endereco_numero, estado, cidade, endereco_complemento, ponto_referencia);
        return "redirect:/conta/endereco";
    }
	
	@GetMapping("/pedidos")
	public String pedidos(Model model, HttpServletRequest request) throws UnsupportedEncodingException {
		String usuarioId = CookieService.getCookie(request, "usuarioId");
		Long userId = Long.parseLong(usuarioId);
		Usuario usuario = userRepository.findById(userId);
		
		List<Pedidos> pedidos = pedidosRepository.findByUserId(userId);
		
		model.addAttribute("pedidos", pedidos);
		model.addAttribute("primeiroNome", usuario.getNome().split(" ")[0]);
		return "meus-pedidos";
	}
	
	@GetMapping("/vendas")
	public String vendas(Model model, HttpServletRequest request) throws UnsupportedEncodingException {
		String usuarioId = CookieService.getCookie(request, "usuarioId");
		if(usuarioId != null) {
			Usuario usuario = userRepository.findById(Long.parseLong(usuarioId));
			if(usuario != null) {
				model.addAttribute("primeiroNome", usuario.getNome().split(" ")[0]);
			}
		}
		return "minhas-vendas";
	}
	
	@Transactional
	@GetMapping("/favoritos")
	public String favoritos(Model model, HttpServletRequest request) throws UnsupportedEncodingException {
		String usuarioId = CookieService.getCookie(request, "usuarioId");
		Long userId = Long.parseLong(usuarioId);
		
		Usuario usuario = userRepository.findById(userId);
		if(usuario != null) {
			model.addAttribute("primeiroNome", usuario.getNome().split(" ")[0]);
		}
		
		List<Favoritos> itensFavoritos = favoritosRepository.findByUserId(userId);
		List<Produto> produtos = itensFavoritos.stream()
				.map(item -> produtoRepository.findById(item.getProdutoId()).orElse(null))
				.filter(produto -> produto != null).collect(Collectors.toList());
		model.addAttribute("produtos", produtos);
		
		return "favoritos";
	}

}
