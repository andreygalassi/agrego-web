package br.com.agrego.model;

import java.io.Serializable;
import java.util.Objects;

import br.com.agrego.model.enuns.EnumAcao;
import br.com.agrego.model.enuns.EnumRecurso;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "aut_acesso")
public class Acesso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "perfil_id")
	private Perfil perfil;

	@Enumerated(EnumType.STRING)
	private EnumRecurso recurso;

	@Enumerated(EnumType.STRING)
	private EnumAcao acao;
	
	public String getRole() {
		return String.format("%s_%s", recurso.name(), acao.name());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EnumRecurso getRecurso() {
		return recurso;
	}

	public void setRecurso(EnumRecurso recurso) {
		this.recurso = recurso;
	}

	public EnumAcao getAcao() {
		return acao;
	}

	public void setAcao(EnumAcao acao) {
		this.acao = acao;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	@Override
	public int hashCode() {
		return Objects.hash(acao, perfil, recurso);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Acesso other = (Acesso) obj;
		return acao == other.acao && Objects.equals(perfil, other.perfil) && recurso == other.recurso;
	}

	@Override
	public String toString() {
		return String.format("Acesso [id=%s, perfil=%s, recurso=%s, acao=%s]", id, perfil, recurso, acao);
	}
	
}
