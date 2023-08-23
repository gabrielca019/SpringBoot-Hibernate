package com.springboot.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.springboot.entities.Categoria;
import com.springboot.entities.ItemPedido;
import com.springboot.entities.Pedido;
import com.springboot.entities.Produto;
import com.springboot.entities.Usuario;
import com.springboot.enums.StatusPedido;
import com.springboot.repositories.ICategoriaRepository;
import com.springboot.repositories.IItemPedidoRepository;
import com.springboot.repositories.IPedidoRepository;
import com.springboot.repositories.IProdutoRepository;
import com.springboot.repositories.IUsuarioRepository;

@Configuration
@Profile("test")
public class testConfig implements CommandLineRunner {
	
	@Autowired
	private IUsuarioRepository usuarioRepository;
	@Autowired
	private IPedidoRepository pedidoRepository;
	@Autowired
	private ICategoriaRepository categoriaRepository;
	@Autowired
	private IProdutoRepository produtoRepository;
	@Autowired
	private IItemPedidoRepository itemPedidoRepository;

	@Override
	public void run(String... args) throws Exception {
		Usuario us1 = new Usuario(null, "Maria Brown", "maria@gmail.com", "988888888", "123456"); 
		Usuario us2 = new Usuario(null, "Alex Green", "alex@gmail.com", "977777777", "123456"); 
		
		usuarioRepository.saveAll(Arrays.asList(us1, us2));
		
		Pedido pe1 = new Pedido(null, Instant.parse("2019-06-20T19:53:07Z"), StatusPedido.CANCELADO, us1); 
		Pedido pe2 = new Pedido(null, Instant.parse("2019-07-21T03:42:10Z"), StatusPedido.ENTREGANDO, us2); 
		Pedido pe3 = new Pedido(null, Instant.parse("2019-07-22T15:21:22Z"), StatusPedido.PAGO, us1); 
		
		pedidoRepository.saveAll(Arrays.asList(pe1, pe2, pe3));
		
		Categoria ca1 = new Categoria(null, "Eletrônicos"); 
		Categoria ca2 = new Categoria(null, "Livros"); 
		Categoria ca3 = new Categoria(null, "Computadores"); 

		categoriaRepository.saveAll(Arrays.asList(ca1, ca2, ca3));
		
		Produto pr1 = new Produto(null, "The Lord of the Rings", "Lorem ipsum dolor sit amet, consectetur.", 90.5, ""); 
		Produto pr2 = new Produto(null, "Smart TV", "Nulla eu imperdiet purus. Maecenas ante.", 2190.0, ""); 
		Produto pr3 = new Produto(null, "Macbook Pro", "Nam eleifend maximus tortor, at mollis.", 1250.0, ""); 
		Produto pr4 = new Produto(null, "PC Gamer", "Donec aliquet odio ac rhoncus cursus.", 1200.0, ""); 
		Produto pr5 = new Produto(null, "Rails for Dummies", "Cras fringilla convallis sem vel faucibus.", 100.99, "");
		
		produtoRepository.saveAll(Arrays.asList(pr1, pr2, pr3, pr4, pr5));
		
		pr1.getCategorias().add(ca2);
		pr2.getCategorias().add(ca1);
		pr2.getCategorias().add(ca3);
		pr3.getCategorias().add(ca3);
		pr4.getCategorias().add(ca3);
		pr5.getCategorias().add(ca2);
		
		produtoRepository.saveAll(Arrays.asList(pr1, pr2, pr3, pr4, pr5));
		
		ItemPedido ip1 = new ItemPedido(pe1, pr1, 2, pr1.getPreco()); 
		ItemPedido ip2 = new ItemPedido(pe1, pr3, 1, pr3.getPreco()); 
		ItemPedido ip3 = new ItemPedido(pe2, pr3, 2, pr3.getPreco()); 
		ItemPedido ip4 = new ItemPedido(pe3, pr5, 2, pr5.getPreco());
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3, ip4));
	}
}