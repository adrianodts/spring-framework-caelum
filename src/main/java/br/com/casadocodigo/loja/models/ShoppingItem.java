package br.com.casadocodigo.loja.models;

import java.io.Serializable;
import java.math.BigDecimal;

import br.com.casadocodigo.loja.model.TipoLivro;

public class ShoppingItem implements Serializable {

	private Produto produto;
	private TipoLivro tipoLivro;
	private Integer idProduto;
	
	public ShoppingItem(Produto produto, TipoLivro tipoLivro) {
		this.produto = produto;
		this.tipoLivro = tipoLivro;
		this.idProduto = produto.getId();
	}
	
	public Produto getProduto() {
		return produto;
	}
	
	public TipoLivro getTipoLivro() {
		return tipoLivro;
	}
	
	public BigDecimal getPrice() {
		return produto.priceFor(tipoLivro);
	}
	
	public BigDecimal getTotal(Integer quantity) {
		return getPrice().multiply(new BigDecimal(quantity));
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((tipoLivro == null) ? 0 : tipoLivro.hashCode());
		result = prime * result
				+ ((idProduto == null) ? 0 : idProduto.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ShoppingItem other = (ShoppingItem) obj;
		if (tipoLivro != other.tipoLivro)
			return false;
		if (idProduto == null) {
			if (other.idProduto != null)
				return false;
		} else if (!idProduto.equals(other.idProduto))
			return false;
		return true;
	}

}
