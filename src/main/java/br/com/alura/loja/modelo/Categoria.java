package br.com.alura.loja.modelo;

import javax.persistence.*;

@Entity
@Table(name = "categorias")
public class Categoria {

	@EmbeddedId
	private CategoriaID id;

	public CategoriaID getId() {
		return id;
	}

	public Categoria() {
	}
	
	public Categoria(String nome) {
		this.id = new CategoriaID(nome, "xpto");
	}

	public String getNome() {
		return this.id.getNome();
	}

}
