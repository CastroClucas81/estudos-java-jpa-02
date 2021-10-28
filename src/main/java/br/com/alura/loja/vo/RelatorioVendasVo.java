package br.com.alura.loja.vo;

import java.time.LocalDate;

//class q representa o relatorio
// ela não é uma entidade. apenas de valor
public class RelatorioVendasVo {
	private String nomeProduto;
	private Long quantidadeVendidade;
	private LocalDate dataUltimaVenda;

	public RelatorioVendasVo(String nomeProduto, Long quantidadeVendidade, LocalDate dataUltimaVenda) {
		this.nomeProduto = nomeProduto;
		this.quantidadeVendidade = quantidadeVendidade;
		this.dataUltimaVenda = dataUltimaVenda;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public Long getQuantidadeVendidade() {
		return quantidadeVendidade;
	}

	public LocalDate getDataUltimaVenda() {
		return dataUltimaVenda;
	}

	@Override
	public String toString() {
		return "RelatorioVendasVo{" +
				"nomeProduto='" + nomeProduto + '\'' +
				", quantidadeVendidade=" + quantidadeVendidade +
				", dataUltimaVenda=" + dataUltimaVenda +
				'}';
	}
}
