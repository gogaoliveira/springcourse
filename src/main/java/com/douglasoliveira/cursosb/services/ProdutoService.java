package com.douglasoliveira.cursosb.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.douglasoliveira.cursosb.domain.Categoria;
import com.douglasoliveira.cursosb.domain.Produto;
import com.douglasoliveira.cursosb.repositories.CategoriaRepository;
import com.douglasoliveira.cursosb.repositories.ProdutoRepository;
import com.douglasoliveira.cursosb.services.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {

	@Autowired
	public ProdutoRepository repo;
	
	@Autowired
	private CategoriaRepository categoriaRepository;

	public Produto find(Integer id) {
		Optional<Produto> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Produto.class.getName()));
	}
	
	public Page<Produto> search(String nome, List<Integer> ids,Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		List<Categoria> categorias = categoriaRepository.findAllById(ids);
		return repo.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageRequest);
		
	}

}
