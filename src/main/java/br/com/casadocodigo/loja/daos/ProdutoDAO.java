package br.com.casadocodigo.loja.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.casadocodigo.loja.models.Produto;

@Repository
public class ProdutoDAO {

	@PersistenceContext 
	private EntityManager manager;
	
	
	public void save(Produto produto){
		manager.persist(produto);
	}
	
	public List<Produto> list()
	{
		return manager.createQuery("Select distinct p from Produto p join fetch p.prices").getResultList();
	}
	
}
