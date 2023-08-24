package com.springboot.entities;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.springboot.entities.pk.ItemPedidoPK;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_item_pedido")
public class ItemPedido implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private ItemPedidoPK id = new ItemPedidoPK();
	private double preco;
	private int quantidade;
	
	public ItemPedido() {}
	
	public ItemPedido(Pedido pedido, Produto produto, int quantidade, double preco) {
		super();
		this.id.setPedido(pedido);
		this.id.setProduto(produto);
		this.preco = preco;
		this.quantidade = quantidade;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public int getQuantidade() {
		return quantidade;
	}
	
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public void setPedido(Pedido pedido) {
		this.id.setPedido(pedido);
	}
	
	@JsonIgnore
	public Pedido getPedido() {
		return this.id.getPedido();
	}

	public void setProduto(Produto produto) {
		this.id.setProduto(produto);
	}

	@JsonIgnore
	public Produto getProduto() {
		return this.id.getProduto();
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemPedido other = (ItemPedido) obj;
		return Objects.equals(id, other.id);
	}
	
	public double getSubTotal() {
		return this.preco * this.quantidade;
	}
	
}