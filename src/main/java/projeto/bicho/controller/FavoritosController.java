package projeto.bicho.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.StaleObjectStateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import projeto.bicho.model.Favoritos;
import projeto.bicho.repository.FavoritosRepository;
import projeto.bicho.service.CookieService;

@Controller
public class FavoritosController {

	@Autowired
	private FavoritosRepository favoritosRepository;
	
	@GetMapping("/favoritos/adicionar")
	public String adicionarFavorito(HttpServletRequest request, @RequestParam Long produtoId) {
		try {
			String usuarioId = CookieService.getCookie(request, "usuarioId");
			if(usuarioId == null || usuarioId.isEmpty()) {
				return "redirect:/login";
			}
			Long userId = Long.parseLong(usuarioId);
			
			Favoritos produtoNosFavoritos = favoritosRepository.findByUserIdAndProdutoId(userId, produtoId);
			if(produtoNosFavoritos == null) {
				Favoritos favoritos = new Favoritos();
				favoritos.setUserId(userId);
				favoritos.setProdutoId(produtoId);
				favoritosRepository.save(favoritos);
			}
			
			return "redirect:/favoritos";
					
		} catch(Exception e) {
			e.printStackTrace();
			return "redirect:/erro";
		}
	}
	
	@Transactional
	@DeleteMapping("/favoritos/remover")
	@ResponseBody
	public String removerFavorito(HttpServletRequest request, @RequestParam Long produtoId) {
	    try {
	        String usuarioId = CookieService.getCookie(request, "usuarioId");
	        if (usuarioId == null || usuarioId.isEmpty()) {
	            return "Não autorizado";
	        }

	        Long userId = Long.parseLong(usuarioId);

	        Favoritos favorito = favoritosRepository.findByUserIdAndProdutoId(userId, produtoId);
	        if (favorito != null) {
	            favoritosRepository.delete(favorito);
	        }

	        return "redirect:/favoritos";
	    } catch (StaleObjectStateException e) {
	        return "Erro de concorrência ao remover o favorito. Tente novamente.";
	    } catch (Exception e) {
	        e.printStackTrace();
	        return "Erro ao remover dos favoritos.";
	    }
	}

	
	@GetMapping("/favoritos/lista")
	@ResponseBody
	public List<Long> obterFavoritos(HttpServletRequest request) throws UnsupportedEncodingException{
		String usuarioId = CookieService.getCookie(request, "usuarioId");
		if(usuarioId == null || usuarioId.isEmpty()) {
			return new ArrayList<>();
		}
		
		Long userId = Long.parseLong(usuarioId);
		return favoritosRepository.findAllByUserId(userId).stream()
				.map(Favoritos::getProdutoId)
				.collect(Collectors.toList());
	}
	
}
