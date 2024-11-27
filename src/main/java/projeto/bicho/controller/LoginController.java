package projeto.bicho.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import projeto.bicho.model.Usuario;
import projeto.bicho.repository.UsuarioRepository;
import projeto.bicho.service.CookieService;

@Controller
public class LoginController {
	
	@Autowired
	private UsuarioRepository userRepository;
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/area-cliente")
	public String areaCliente(Model model, HttpServletRequest request) throws UnsupportedEncodingException {
		model.addAttribute("nome", (CookieService.getCookie(request, "nomeUsuario")).split(" ")[0]);
		return "area-cliente";
	}
	
	@PostMapping("/login")
	public String loginUsuario(Usuario usuario, HttpServletResponse response) throws UnsupportedEncodingException {
		Usuario user = this.userRepository.login(usuario.getEmail(), usuario.getSenha());
		if(user != null) {
			CookieService.setCookie(response, "usuarioId", String.valueOf(user.getId()), 10000);
			CookieService.setCookie(response, "nomeUsuario", String.valueOf(user.getNome()), 10000);
			return "redirect:/area-cliente";
		}
		
		return "login";
	}
	
	@GetMapping("/sair")
	public String sair(HttpServletResponse response) throws UnsupportedEncodingException {
		CookieService.setCookie(response, "usuarioId", "", 0);		
		CookieService.setCookie(response, "nomeUsuario", "", 0);
		return "redirect:/login";
	}	
	
	@GetMapping("/cadastrar")
	public String cadastrar() {
		return "cadastro";
	}
	
	@RequestMapping(value = "/cadastrar", method = RequestMethod.POST)
	public String cadastroUsuario(@Valid Usuario usuario, BindingResult result) {
		
		if(result.hasErrors()) {
			return "redirect:/cadastrar";
		}

		userRepository.save(usuario);
		return "redirect:/login";
	}
	
	@GetMapping("/recuperar-senha")
	public String recuperaSenha() {
		return "recupera-senha";
	}
}
