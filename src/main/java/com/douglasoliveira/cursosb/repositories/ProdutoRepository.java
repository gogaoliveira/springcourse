package com.douglasoliveira.cursosb.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.douglasoliveira.cursosb.domain.Categoria;
import com.douglasoliveira.cursosb.domain.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer>{

	//	@Transactional(readOnly = true)
//	@Query("SELECT DISTINCT obj FROM Produto obj INNER JOIN obj.categorias cat where obj.nome LIKE %:nome% AND cat IN :categorias")
//	Page<Produto> search(@Param("nome") String nome,@Param("categorias") List<Categoria> categorias, Pageable pageRequest);
//
//  codigo acima pode ser substituido por esse
	
	@Transactional(readOnly = true)
	Page<Produto> findDistinctByNomeContainingAndCategoriasIn(String nome, List<Categoria> categorias, Pageable pageRequest);
	

}
