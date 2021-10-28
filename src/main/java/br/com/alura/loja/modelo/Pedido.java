package br.com.alura.loja.modelo;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pedidos")
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "valor_total")
	private BigDecimal valorTotal = BigDecimal.ZERO;
	private LocalDate data = LocalDate.now();

	//lazy impede do jpa fazer vários inner joins no automático
	@ManyToOne(fetch = FetchType.LAZY)
	private Cliente cliente;

	//tomar cuidado cm relacionamento bidirecional...
	// no OneToMany usar o mappedBy
	// "pedido" ta lá em ItemPedido
	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
	private List<ItemPedido> itens = new ArrayList<>();

	public Pedido() {}

	public Pedido(Cliente cliente) {
		this.cliente = cliente;
	}

	public void adicionarItem(ItemPedido item) {
		//item conhece o pedido
		item.setPedido(this);

		//pedido conhece o item
		this.itens.add(item);

		//também preciso incrementar o valor total
		this.valorTotal = this.valorTotal.add(item.getValor());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
}
