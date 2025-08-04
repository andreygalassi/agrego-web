package br.com.agrego.model.dto;

import java.util.Set;

public class LivroFiltro {

	private Long id;
	private String titulo;
	private String descricao;
	private Set<String> listaAutor;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
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
	public Set<String> getListaAutor() {
		return listaAutor;
	}
	public void setListaAutor(Set<String> listaAutor) {
		this.listaAutor = listaAutor;
	}
}
