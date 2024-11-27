package projeto.bicho.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import jakarta.servlet.http.HttpServletRequest;
import projeto.bicho.service.CookieService;

@ControllerAdvice
public class GlobalController {
	
	@ModelAttribute("logado")
	public boolean logado(HttpServletRequest request) throws UnsupportedEncodingException{
		return CookieService.getCookie(request, "usuarioId") != null;
	}

}
