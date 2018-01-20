package br.com.casadocodigo.loja.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import br.com.casadocodigo.loja.model.TipoLivro;

@Entity
public class Produto {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@NotBlank
	public String titulo;
	@Lob 
	@NotBlank
	public String descricao;
	@Min(30)
	public int paginas;
	@DateTimeFormat
	public Calendar dataLancamento;
	public String sumario;
	@ElementCollection
	private List<Preco> prices = new ArrayList<Preco>();
	public Integer getId() {
		return id;
	}
	
	public BigDecimal priceFor(TipoLivro tipoLivro) {
		return prices
			.stream()
			.filter(price -> price.getTipoLivro().equals(tipoLivro))
			.findFirst().get().getValor();
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public int getPaginas() {
		return paginas;
	}
	public void setPaginas(int paginas) {
		this.paginas = paginas;
	}
	public Calendar getDataLancamento() {
		return dataLancamento;
	}
	public void setDataLancamento(Calendar dataLancamento) {
		this.dataLancamento = dataLancamento;
	}
	public String getSumario() {
		return sumario;
	}
	public void setSumario(String sumario) {
		this.sumario = sumario;
	}
	public List<Preco> getPrices() {
		return prices;
	}
	public void setPrices(List<Preco> prices) {
		this.prices = prices;
	}
	@Override
	public String toString() {
		return "Produto [id=" + id + ", titulo=" + titulo + ", descricao=" + descricao + ", paginas=" + paginas
				+ ", dataLancamento=" + dataLancamento + ", sumario=" + sumario + ", prices=" + prices + "]";
	}
	
}
