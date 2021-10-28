package br.com.alura.loja.modelo;

import javax.persistence.Embeddable;

//esta classe é embutível
@Embeddable
public class DadosPessoais {
	private String nome;
	private String cpf;

	// também deve ter um construtor de default
	public DadosPessoais() {}

	public DadosPessoais(String nome, String cpf) {
		this.nome = nome;
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public String getCpf() {
		return cpf;
	}

}
