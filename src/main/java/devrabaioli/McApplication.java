package devrabaioli;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import devrabaioli.domain.Categoria;
import devrabaioli.domain.Cidade;
import devrabaioli.domain.Cliente;
import devrabaioli.domain.Endereco;
import devrabaioli.domain.Estado;
import devrabaioli.domain.ItemPedido;
import devrabaioli.domain.Pagamento;
import devrabaioli.domain.PagamentoComBoleto;
import devrabaioli.domain.PagamentoComCartao;
import devrabaioli.domain.Pedido;
import devrabaioli.domain.Produto;
import devrabaioli.domain.enums.EstadoPagamento;
import devrabaioli.domain.enums.TipoCliente;
import devrabaioli.repositories.CategoriaRepository;
import devrabaioli.repositories.CidadeRepository;
import devrabaioli.repositories.ClienteRepository;
import devrabaioli.repositories.EnderecoRepository;
import devrabaioli.repositories.EstadoRepository;
import devrabaioli.repositories.ItemPedidoRepository;
import devrabaioli.repositories.PagamentoRepository;
import devrabaioli.repositories.PedidoRepository;
import devrabaioli.repositories.ProdutoRepository;

@SpringBootApplication
public class McApplication implements CommandLineRunner{

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(McApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "Mouse", 300.00);
		Produto p2 = new Produto(null, "Impressora", 500.00);
		Produto p3 = new Produto(null, "Computador", 1200.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		
		Estado est1 = new Estado(null, "MT");
		Estado est2 = new Estado(null, "SP");
		
		Cidade c1 = new Cidade(null, "Barra do garças", est1);
		Cidade c2 = new Cidade(null, "Campinas", est2);
		Cidade c3 = new Cidade(null, "Sao Paulo", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);

		cli1.getTELEFONES().addAll(Arrays.asList("27363323", "93838393"));

		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim", "38220834", c1, cli1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", c2, cli1);

		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), e1, cli1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), e2, cli1);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
     	Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("30/09/2017 00:00"), null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1,ped2));
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);

		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));

		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));


		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		estadoRepository.saveAll(Arrays.asList(est1,est2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1,pagto2));
		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
	}
	

	@Configuration
	@EnableWebSecurity
	public class SecurityConfig {

		@Bean
		public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

			http.headers().frameOptions().disable();
			http.cors().and().csrf().disable();
			http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
			http.authorizeHttpRequests((auth) -> auth.anyRequest().permitAll());

			return http.build();
		}

		@Bean
		CorsConfigurationSource corsConfigurationSource() {
			CorsConfiguration configuration = new CorsConfiguration().applyPermitDefaultValues();
			configuration.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "DELETE", "OPTIONS"));
			final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
			source.registerCorsConfiguration("/**", configuration);
			return source;
		}

	}




	
}
