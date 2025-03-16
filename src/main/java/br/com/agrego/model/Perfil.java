package br.com.agrego.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Perfil {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private Boolean visualizar;
	private Boolean criar;
	private Boolean alterar;
	private Boolean deletar;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Boolean getVisualizar() {
		return visualizar;
	}
	public void setVisualizar(Boolean visualizar) {
		this.visualizar = visualizar;
	}
	public Boolean getCriar() {
		return criar;
	}
	public void setCriar(Boolean criar) {
		this.criar = criar;
	}
	public Boolean getAlterar() {
		return alterar;
	}
	public void setAlterar(Boolean alterar) {
		this.alterar = alterar;
	}
	public Boolean getDeletar() {
		return deletar;
	}
	public void setDeletar(Boolean deletar) {
		this.deletar = deletar;
	}

}
