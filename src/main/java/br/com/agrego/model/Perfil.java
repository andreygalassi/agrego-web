package br.com.agrego.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import br.com.agrego.model.enuns.EnumAcao;
import br.com.agrego.model.enuns.EnumRecurso;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "aut_perfil")
public class Perfil implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;

	@OneToMany(mappedBy = "perfil", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Acesso> listaAcesso;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return StringUtils.upperCase(nome);
	}

	public void setNome(String nome) {
		this.nome = StringUtils.upperCase(nome);
	}

	public List<Acesso> getListaAcesso() {
		return listaAcesso;
	}
	public void setListaAcesso(List<Acesso> listaAcesso) {
		this.listaAcesso = listaAcesso;
	}
	public void addAcesso(EnumRecurso recurso, EnumAcao acao) {
		if (this.listaAcesso==null) this.listaAcesso = new ArrayList<>();
		Acesso acesso = new Acesso();
		acesso.setPerfil(this);
		acesso.setRecurso(recurso);
		acesso.setAcao(acao);
		this.listaAcesso.add(acesso);
	}
	
	public Set<String> getListaRolesName(){
		return this.listaAcesso.stream().map(r -> r.getRole()).collect(Collectors.toSet());
	}

	@Override
	public String toString() {
		return String.format("Perfil [id=%s, nome=%s]", id, nome);
	}
}
