package com.springboot.enums;

public enum StatusPedido {
	ESPERANDO_PAGAMENTO(1),
	PAGO(2),
	ENVIADO(3),
	ENTREGANDO(4),
	CANCELADO(5);
	
	private int code;
	
	private StatusPedido(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}
	
	public static StatusPedido valueOf(int code) {
		for(StatusPedido ps : StatusPedido.values()) {
			if(ps.getCode() == code)
				return ps;
		}
		throw new IllegalArgumentException("Código inválido de status do pedido!");
	}
}