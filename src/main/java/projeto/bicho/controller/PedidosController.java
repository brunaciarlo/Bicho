package projeto.bicho.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import projeto.bicho.model.Carrinho;
import projeto.bicho.model.PedidoItem;
import projeto.bicho.model.Pedidos;
import projeto.bicho.model.Produto;
import projeto.bicho.repository.CarrinhoRepository;
import projeto.bicho.repository.PedidoItemRepository;
import projeto.bicho.repository.PedidosRepository;
import projeto.bicho.repository.ProdutoRepository;
import projeto.bicho.service.CookieService;

@Controller
public class PedidosController {
	
	@Autowired
	private CarrinhoRepository carrinhoRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private PedidosRepository pedidosRepository;
	
	@Autowired
	private PedidoItemRepository pedidoItemRepository;
	
	@PostMapping("/finalizar")
	public String finalizarPedido(@RequestParam Double valorTotal, HttpServletRequest request, Model model) throws UnsupportedEncodingException {
		String usuarioId = CookieService.getCookie(request, "usuarioId");
		if(usuarioId == null || usuarioId.isEmpty()) {
			return "redirect:/login";
		}
		
		Long userId = Long.parseLong(usuarioId);
		List<Carrinho> itensCarrinho = carrinhoRepository.findByUserId(userId);
        List<Produto> produtos = itensCarrinho.stream()
                .map(item -> produtoRepository.findById(item.getProdutoId()).orElse(null))
                .filter(produto -> produto != null)
                .collect(Collectors.toList());
		
		Pedidos pedido = new Pedidos();
		pedido.setUserId(userId);
		pedido.setValorTotal(valorTotal);
		pedido.setStatus("Pagamento aprovado");
		pedido = pedidosRepository.save(pedido);
		
        for (Produto produto : produtos) {
            PedidoItem item = new PedidoItem();
            item.setPedido(pedido);
            item.setNomeProduto(produto.getNome());
            item.setImagemProduto(produto.getImagem());
            item.setPrecoProduto(produto.getPreco());
            pedidoItemRepository.save(item);
        }
		
		carrinhoRepository.deleteAll(itensCarrinho);
		
		model.addAttribute("pedido", pedido);
		return "confirmacao-pedido";
	}

}
