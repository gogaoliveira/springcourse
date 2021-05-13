package com.douglasoliveira.cursosb.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douglasoliveira.cursosb.domain.ItemPedido;
import com.douglasoliveira.cursosb.domain.PagamentoComBoleto;
import com.douglasoliveira.cursosb.domain.Pedido;
import com.douglasoliveira.cursosb.domain.enums.EstadoPagamento;
import com.douglasoliveira.cursosb.repositories.ClienteRepository;
import com.douglasoliveira.cursosb.repositories.ItemPedidoRepository;
import com.douglasoliveira.cursosb.repositories.PagamentoRepository;
import com.douglasoliveira.cursosb.repositories.PedidoRepository;
import com.douglasoliveira.cursosb.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	public PedidoRepository repo;
	
	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;

	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	private ClienteService clienteService;
	
	
	public Pedido find(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}
	
	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setInstant(new Date());
		obj.setCliente(clienteService.find(obj.getCliente().getId()));
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		if (obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagto, obj.getInstant());
		}
		obj = repo.save(obj);
		pagamentoRepository.save(obj.getPagamento());
		for (ItemPedido ip : obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setProduto(produtoService.find(ip.getProduto().getId()));
			ip.setPreco(ip.getProduto().getPreco());
			ip.setPedido(obj);
		}
		itemPedidoRepository.saveAll(obj.getItens());
		System.out.println(obj);
		return obj;
	}

}
