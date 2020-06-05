package com.lucaswilliam.cursospringcompleto.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.lucaswilliam.cursospringcompleto.domains.Categoria;
import com.lucaswilliam.cursospringcompleto.domains.Cidade;
import com.lucaswilliam.cursospringcompleto.domains.Cliente;
import com.lucaswilliam.cursospringcompleto.domains.Endereco;
import com.lucaswilliam.cursospringcompleto.domains.Estado;
import com.lucaswilliam.cursospringcompleto.domains.ItemPedido;
import com.lucaswilliam.cursospringcompleto.domains.Pagamento;
import com.lucaswilliam.cursospringcompleto.domains.PagamentoComBoleto;
import com.lucaswilliam.cursospringcompleto.domains.PagamentoComCartao;
import com.lucaswilliam.cursospringcompleto.domains.Pedido;
import com.lucaswilliam.cursospringcompleto.domains.Produto;
import com.lucaswilliam.cursospringcompleto.domains.enums.EstadoPagamento;
import com.lucaswilliam.cursospringcompleto.domains.enums.TipoCliente;
import com.lucaswilliam.cursospringcompleto.repositories.CategoriaRepository;
import com.lucaswilliam.cursospringcompleto.repositories.CidadeRepository;
import com.lucaswilliam.cursospringcompleto.repositories.ClienteRepository;
import com.lucaswilliam.cursospringcompleto.repositories.EnderecoRepository;
import com.lucaswilliam.cursospringcompleto.repositories.EstadoRepository;
import com.lucaswilliam.cursospringcompleto.repositories.ItemPedidoRepository;
import com.lucaswilliam.cursospringcompleto.repositories.PagamentoRepository;
import com.lucaswilliam.cursospringcompleto.repositories.PedidoRepository;
import com.lucaswilliam.cursospringcompleto.repositories.ProdutoRepository;

@Configuration
public class Instantiate implements CommandLineRunner {
	//Repositories
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		//Instanciação das categorias
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		Categoria cat3 = new Categoria(null, "Cama, mesa e banho");
		Categoria cat4 = new Categoria(null, "Eletrônicos");
		Categoria cat5 = new Categoria(null, "Jardinagem");
		Categoria cat6 = new Categoria(null, "Decoração");
		Categoria cat7 = new Categoria(null, "Perfumaria");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p3, p2));
		cat2.getProdutos().add(p2);
		
		p1.getCategorias().add(cat1);
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().add(cat1);		
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		//Instanciação de cidades e estados
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().add(c1);
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		//Instanciação de clientes, endereços e telefones
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);
		
		cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));
		
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 203", "Jardim", "38220834", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
		
		//Instanciação de Pedidos e pagamentos
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), e1, cli1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), e2, cli1);
		
		Pagamento pag1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, 6, ped1);
		Pagamento pag2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, sdf.parse("20/10/2017 00:00"), null, ped2);
		
		ped1.setPagamento(pag1);
		ped2.setPagamento(pag2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pag1, pag2));
		
		
		//Instanciação itens Pedidos
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
	}

}
