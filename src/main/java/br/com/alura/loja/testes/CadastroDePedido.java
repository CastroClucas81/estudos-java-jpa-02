package br.com.alura.loja.testes;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ClienteDao;
import br.com.alura.loja.dao.PedidoDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.*;
import br.com.alura.loja.util.JPAUtil;
import br.com.alura.loja.vo.RelatorioVendasVo;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class CadastroDePedido {
	public static void main(String[] args) {
		popularBancoDeDados();

		//recuperando o produto
		EntityManager entityManager = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(entityManager);
		ClienteDao clienteDao = new ClienteDao(entityManager);

		Produto produto = produtoDao.buscarPorId(1l);
		Produto produto2 = produtoDao.buscarPorId(2l);
		Produto produto3 = produtoDao.buscarPorId(3l);
		Cliente cliente = clienteDao.buscarPorId(1l);

		entityManager.getTransaction().begin();

		Pedido pedido = new Pedido(cliente);

		//adicionar itens ao pedido
		pedido.adicionarItem(new ItemPedido(10, pedido, produto));
		pedido.adicionarItem(new ItemPedido(40, pedido, produto2));

		Pedido pedido2 = new Pedido(cliente);
		pedido.adicionarItem(new ItemPedido(2, pedido, produto3));

		PedidoDao pedidoDao = new PedidoDao(entityManager);
		pedidoDao.cadastrar(pedido);

		entityManager.getTransaction().commit();

		BigDecimal totalVendido = pedidoDao.valorTotalVendido();
		System.out.println("Valor total vendido: " + totalVendido);

		List<RelatorioVendasVo> relatorio = pedidoDao.relatorioDeVendas();
		relatorio.forEach(System.out::println);
	}

	//só pra popular os dados
	private static void popularBancoDeDados() {
		Categoria celulares = new Categoria("CELULARES");
		Categoria videogames = new Categoria("VIDEOGAMES");
		Categoria informatica = new Categoria("INFORMATICA");

		Produto celular = new Produto("Xiaomi Redmi", "Muito legal", new BigDecimal("800"), celulares);
		Produto videogame = new Produto("PS5", "viciante", new BigDecimal("3000"), celulares );
		Produto macbox = new Produto("Macbook", "potente", new BigDecimal("6759"), celulares );

		Cliente cliente = new Cliente("Lucas", "123456");

		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		CategoriaDao categoriaDao = new CategoriaDao(em);
		ClienteDao clienteDao = new ClienteDao(em);

		em.getTransaction().begin();

		categoriaDao.cadastrar(celulares);
		categoriaDao.cadastrar(videogames);
		categoriaDao.cadastrar(informatica);

		produtoDao.cadastrar(celular);
		produtoDao.cadastrar(videogame);
		produtoDao.cadastrar(macbox);

		clienteDao.cadastrar(cliente);

		em.getTransaction().commit();
		em.close();
	}
}
