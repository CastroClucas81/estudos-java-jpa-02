package br.com.alura.loja.testes;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ClienteDao;
import br.com.alura.loja.dao.PedidoDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.*;
import br.com.alura.loja.util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class PerformanceConsulta {
	public static void main(String[] args) {
		popularBancoDeDados();
		EntityManager entityManager = JPAUtil.getEntityManager();
		PedidoDao pedidoDao = new PedidoDao(entityManager);
		Pedido pedido = pedidoDao.buscarPedidoComCliente(1l);
		entityManager.close();
		System.out.println(pedido.getData());
	}

	public static void popularBancoDeDados() {
		Categoria celulares = new Categoria("CELULARES");
		Categoria videogames = new Categoria("VIDEOGAMES");
		Categoria informatica = new Categoria("INFORMATICA");

		Produto celular = new Produto("Xiaomi Redmi", "Muito legal", new BigDecimal("800"), celulares);
		Produto videogame = new Produto("PS5", "viciante", new BigDecimal("3000"), celulares );
		Produto macboc = new Produto("Macbook", "potente", new BigDecimal("6759"), celulares );

		Cliente cliente = new Cliente("Lucas", "123456");

		Pedido pedido = new Pedido(cliente);
		pedido.adicionarItem(new ItemPedido(10, pedido, celular));
		pedido.adicionarItem(new ItemPedido(40, pedido, videogame));

		Pedido pedido2 = new Pedido(cliente);
		pedido.adicionarItem(new ItemPedido(2, pedido, macboc));

		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		CategoriaDao categoriaDao = new CategoriaDao(em);
		ClienteDao clienteDao = new ClienteDao(em);
		PedidoDao pedidoDao = new PedidoDao(em);

		em.getTransaction().begin();

		categoriaDao.cadastrar(celulares);
		categoriaDao.cadastrar(videogames);
		categoriaDao.cadastrar(informatica);

		produtoDao.cadastrar(celular);
		produtoDao.cadastrar(videogame);
		produtoDao.cadastrar(macboc);

		clienteDao.cadastrar(cliente);

		pedidoDao.cadastrar(pedido);

		em.getTransaction().commit();
		em.close();
	}
}
