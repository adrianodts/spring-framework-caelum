package br.com.casadocodigo.loja.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.casadocodigo.loja.daos.ProdutoDAO;

@Controller
public class ProductsController {
	
	@Autowired
	ProdutoDAO produtoDAO;
	
	@RequestMapping("products/list")
	public ModelAndView List()
	{
		ModelAndView mv = new ModelAndView("list");
		mv.addObject("produtos", produtoDAO.list());
		return mv;
	}
}
