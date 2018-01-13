package br.com.casadocodigo.loja.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo.loja.daos.ProdutoDAO;
import br.com.casadocodigo.loja.model.TipoLivro;
import br.com.casadocodigo.loja.models.Produto;

@Controller
//@RequestMapping("products")
public class ProductController {
	
	@Autowired ProdutoDAO produtoDAO;
	
	@RequestMapping("products/form")
	public ModelAndView form(Produto produto){
		ModelAndView model = new ModelAndView("form");
		model.addObject("types", TipoLivro.values());
		return model;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@Transactional
	public ModelAndView save(Produto produto, RedirectAttributes ra){
		System.out.println("Cadastrando o produto: " + produto);
		produtoDAO.save(produto);
		ModelAndView mv = new ModelAndView("redirect:list");
		ra.addFlashAttribute("sucesso", "Produto cadastrado com sucesso");
		//mv.addObject("sucesso", "Produto cadastrado com sucesso");
		return mv;
	}
}
