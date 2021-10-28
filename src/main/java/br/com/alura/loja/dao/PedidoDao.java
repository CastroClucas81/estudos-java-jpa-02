package br.com.alura.loja.dao;

import br.com.alura.loja.modelo.Pedido;
import br.com.alura.loja.vo.RelatorioVendasVo;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class PedidoDao {

	private EntityManager entityManager;

	public PedidoDao(EntityManager em) {
		this.entityManager = em;
	}

	public void cadastrar(Pedido pedido) {
		this.entityManager.persist(pedido);
	}

	public BigDecimal valorTotalVendido() {
		String jpql = "SELECT SUM(p.valorTotal) FROM Pedido p ";
		return entityManager.createQuery(jpql, BigDecimal.class).getSingleResult();
	}

	public List<RelatorioVendasVo> relatorioDeVendas() {
		//posso instanciar uma class dentro do select
		String jpql = "SELECT " +
						"new br.com.alura.loja.vo.RelatorioVendasVo(" +
						"produto.nome, " +
					  	"SUM(item.quantidade), " +
					    "MAX(pedido.data)) " +
					  "FROM Pedido pedido " +
					  	"JOIN pedido.itens item " +
					  	"JOIN item.produto produto " +
					  "GROUP BY produto.nome " +
					  "ORDER BY item.quantidade DESC ";
		return entityManager.createQuery(jpql, RelatorioVendasVo.class).getResultList();
	}

	public Pedido buscarPedidoComCliente(Long id) {
		//carrega um relacionamento q é lazy E TRANSFORMA PARA EAGER com o JOIN FETCH
		return entityManager.createQuery("SELECT p FROM Pedido p JOIN FETCH p.cliente WHERE p.id = :id", Pedido.class)
				.setParameter("id", id)
				.getSingleResult();
	}
}
