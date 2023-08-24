package com.springboot.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.springboot.enums.StatusPedido;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_pedido")
public class Pedido implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	private Instant data;
	private Integer statusPedido;
	@ManyToOne
	@JoinColumn(name = "id_cliente")
	private Usuario cliente;
	@OneToMany(mappedBy = "id.pedido")
	private Set<ItemPedido> itensPedido = new HashSet<>();
	@OneToOne(mappedBy = "pedido", cascade = CascadeType.ALL)
	private Pagamento pagamento;
	
	public Pedido() {};
	
	public Pedido(Long id, Instant data, StatusPedido statusPedido, Usuario cliente) {
		super();
		this.id = id;
		this.data = data;
		setStatusPedido(statusPedido);
		this.cliente = cliente;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Instant getData() {
		return data;
	}

	public void setData(Instant data) {
		this.data = data;
	}

	public StatusPedido getStatusPedido() {
		return StatusPedido.valueOf(statusPedido);
	}

	public void setStatusPedido(StatusPedido statusPedido) {
		if(statusPedido != null)
			this.statusPedido = statusPedido.getCode();
	}

	public Usuario getCliente() {
		return cliente;
	}

	public void setCliente(Usuario cliente) {
		this.cliente = cliente;
	}
	
	public Set<ItemPedido> getItensPedido() {
		return this.itensPedido;
	}
	
	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
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
		Pedido other = (Pedido) obj;
		return Objects.equals(id, other.id);
	}
	
	public double getValorTotal() {
		double total = 0.0;
		for(ItemPedido ip : itensPedido) 
			total += ip.getSubTotal();
		return total;
	}
	
}