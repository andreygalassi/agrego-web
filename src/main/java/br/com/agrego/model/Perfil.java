package br.com.agrego.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import br.com.agrego.model.enuns.EnumRole;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;

@Entity
public class Perfil implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Enumerated(EnumType.STRING)
	private EnumRole role;
	private boolean visualizar=false;
	private boolean consultar=false;
	private boolean criar=false;
	private boolean alterar=false;
	private boolean deletar=false;
	private boolean administrar=false;
	
	@Transient
	public Set<String> getListRolesName() {
		Set<String> lista = new HashSet<>();
		lista.add(role.toString());
		if (administrar) lista.add(role.toString()+"_ADMINISTRAR");
		if (alterar) lista.add(role.toString()+"_ALTERAR");
		if (consultar) lista.add(role.toString()+"_PESQUISAR");
		if (criar) lista.add(role.toString()+"_CRIAR");
		if (deletar) lista.add(role.toString()+"_DELETAR");
		if (visualizar) lista.add(role.toString()+"_VISUALIZAR");
		return lista;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EnumRole getRole() {
		return role;
	}

	public void setRole(EnumRole role) {
		this.role = role;
	}

	public boolean isVisualizar() {
		return visualizar;
	}

	public void setVisualizar(boolean visualizar) {
		this.visualizar = visualizar;
	}

	public boolean isConsultar() {
		return consultar;
	}

	public void setConsultar(boolean consultar) {
		this.consultar = consultar;
	}

	public boolean isCriar() {
		return criar;
	}

	public void setCriar(boolean criar) {
		this.criar = criar;
	}

	public boolean isAlterar() {
		return alterar;
	}

	public void setAlterar(boolean alterar) {
		this.alterar = alterar;
	}

	public boolean isDeletar() {
		return deletar;
	}

	public void setDeletar(boolean deletar) {
		this.deletar = deletar;
	}

	public boolean isAdministrar() {
		return administrar;
	}

	public void setAdministrar(boolean administrar) {
		this.administrar = administrar;
	}

}
