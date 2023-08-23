package com.springboot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.entities.ItemPedido;
import com.springboot.repositories.IItemPedidoRepository;

@Service
public class ItemPedidoService {
	
	@Autowired
	private IItemPedidoRepository itemPedidoRepository;
	
	public List<ItemPedido> findAll() {
		return itemPedidoRepository.findAll();
	}
	
	public ItemPedido findById(Long id) {
		return itemPedidoRepository.findById(id).get();
	}

}