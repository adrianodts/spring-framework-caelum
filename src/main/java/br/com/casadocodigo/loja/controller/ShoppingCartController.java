package br.com.casadocodigo.loja.controller;

import java.math.BigDecimal;
import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import br.com.casadocodigo.loja.daos.ProdutoDAO;
import br.com.casadocodigo.loja.model.TipoLivro;
import br.com.casadocodigo.loja.models.PaymentData;
import br.com.casadocodigo.loja.models.Produto;
import br.com.casadocodigo.loja.models.ShoppingCart;
import br.com.casadocodigo.loja.models.ShoppingItem;

@Controller
@RequestMapping("/shopping")
public class ShoppingCartController {

	@Autowired ProdutoDAO produtoDAO;
	@Autowired ShoppingCart shoppingCart;
	@Autowired RestTemplate restTemplate;
	

	@RequestMapping(value="/checkout",	method=RequestMethod.POST)
	public Callable<String> checkout() {
		return () -> {
			BigDecimal total = shoppingCart.getTotal();
			String uriToPay	= "http://book-payment.herokuapp.com/payment";
			try	{
				String response = restTemplate.postForObject(uriToPay,
				new	PaymentData(total),	String.class);
				System.out.println(response);
				shoppingCart.clear();
				return	"redirect:/products/list";
			}catch(HttpClientErrorException	exception){
				System.out.println("Ocorreu	um erro	ao criar o Pagamento: "	+exception.getMessage());
				return	"redirect:/shopping";
			}
		};
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView add(Integer idProduto, @RequestParam TipoLivro tipoLivro)
	{
		ShoppingItem item = createItem(idProduto, tipoLivro);
		shoppingCart.add(item);
		return new ModelAndView("shoppingCart/items");
	}
	
	private ShoppingItem createItem(Integer idProduto, TipoLivro tipoLivro)
	{
		Produto produto = produtoDAO.find(idProduto);
		ShoppingItem item = new ShoppingItem(produto, tipoLivro);
		return item;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String list() {
		return "shoppingCart/items";
	}
}