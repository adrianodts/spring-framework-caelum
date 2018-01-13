package br.com.casadocodigo.loja.controller;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo.loja.daos.ProdutoDAO;
import br.com.casadocodigo.loja.model.TipoLivro;
import br.com.casadocodigo.loja.models.Produto;
import br.com.casadocodigo.loja.validations.ProductValidator;

@Controller
//@RequestMapping("products")
public class ProductController {
	
	@Autowired ProdutoDAO produtoDAO;
	
	@InitBinder
	public void initBinder(WebDataBinder binder)
	{
		binder.addValidators(new ProductValidator());
	}
	
	@RequestMapping("products/form")
	public ModelAndView form(Produto produto){
		ModelAndView model = new ModelAndView("form");
		model.addObject("types", TipoLivro.values());
		return model;
	}
	
	@RequestMapping(value="products/save", method=RequestMethod.POST)
	@Transactional
	public ModelAndView save(@Valid Produto produto, 
			BindingResult bindingResult,
			RedirectAttributes ra){
		
		if(bindingResult.hasErrors()){
			return form(produto);
		}
		
		System.out.println("Cadastrando o produto: " + produto);
		produtoDAO.save(produto);
		ModelAndView mv = new ModelAndView("redirect:list");
		ra.addFlashAttribute("sucesso", "Produto cadastrado com sucesso");
		//mv.addObject("sucesso", "Produto cadastrado com sucesso");
		return mv;
	}
	
//	@RequestMapping(method=RequestMethod.POST)
//	@Transactional
//	public ModelAndView save(@Valid Produto produto, 
//			BindingResult bindingResult,
//			RedirectAttributes ra){
//		
//		if(bindingResult.hasErrors()){
//			return form(produto);
//		}
//		
//		System.out.println("Cadastrando o produto: " + produto);
//		produtoDAO.save(produto);
//		ModelAndView mv = new ModelAndView("redirect:list");
//		ra.addFlashAttribute("sucesso", "Produto cadastrado com sucesso");
//		//mv.addObject("sucesso", "Produto cadastrado com sucesso");
//		return mv;
//	}
}
