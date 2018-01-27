package br.com.casadocodigo.loja.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.com.casadocodigo.loja.daos.ProdutoDAO;
import br.com.casadocodigo.loja.models.Produto;

@Controller
@RequestMapping("products")
public class ProductsController {
	
	@Autowired
	ProdutoDAO produtoDAO;
	
	@Cacheable(value="lastProducts")
	@RequestMapping(method = RequestMethod.GET, value="/list")
	public ModelAndView List()
	{
		ModelAndView mv = new ModelAndView("list");
		mv.addObject("produtos", produtoDAO.list());
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.GET,value="/json")
	@ResponseBody
	public List<Produto> listJson()	{
		return	produtoDAO.list();
	}
}