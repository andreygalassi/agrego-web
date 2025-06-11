package br.com.agrego.model.dto;

import br.com.agrego.model.Autor;
import br.com.agrego.model.Livro;

public record LivroResponse(Long id, String titulo, String descricao, AutorLivroResponse autor) {
	
	public record AutorLivroResponse(Long id, String nome) {
		public static AutorLivroResponse valueOf(Autor autor) {
			if (autor==null) return null;
			return new AutorLivroResponse(autor.getId(), autor.getNome());
		}
	}
	
	public static LivroResponse valueOf(Livro livro) {
		if (livro==null) return null;
		AutorLivroResponse autor = AutorLivroResponse.valueOf(livro.getAutor());
		return new LivroResponse(livro.getId(), livro.getTitulo(), livro.getDescricao(), autor);
		
	}
	
}
