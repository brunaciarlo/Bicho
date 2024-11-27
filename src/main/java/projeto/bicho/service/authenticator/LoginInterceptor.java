package projeto.bicho.service.authenticator;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import projeto.bicho.service.CookieService;

@Component
public class LoginInterceptor implements HandlerInterceptor {
	
	String[] paginasPrivadas = {"/area-cliente", "/carrinho", "/favoritos", "/finalizar-compra", "/endereco", "/pedidos", "/conta", "/vendas"};
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
		
		String uri = request.getRequestURI();
		
		for(String pagina : paginasPrivadas) {
			if(uri.startsWith(pagina)) {
				if(CookieService.getCookie(request, "usuarioId") != null) {
					return true;
				}
				
				response.sendRedirect("/login");
				return false;
			}
		}
		return true;		
	}

}
