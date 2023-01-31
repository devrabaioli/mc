package devrabaioli;




import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import devrabaioli.domain.Categoria;
import devrabaioli.domain.Cidade;
import devrabaioli.domain.Estado;
import devrabaioli.domain.Produto;
import devrabaioli.repositories.CategoriaRepository;
import devrabaioli.repositories.CidadeRepository;
import devrabaioli.repositories.EstadoRepository;
import devrabaioli.repositories.ProdutoRepository;

@SpringBootApplication
public class McApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;

	public static void main(String[] args) {
		SpringApplication.run(McApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "Computador", 340.00);
		Produto p2 = new Produto(null, "Impressora", 31.00);
		Produto p3 = new Produto(null, "Rato", 3.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		
		Estado est1 = new Estado(null, "Mato Grosso");
		Estado est2 = new Estado(null, "Sao Paulo");
		
		Cidade cid1 = new Cidade(null, "Cuiaba", est1);
		est1.getCidades().addAll(Arrays.asList(cid1));
		
		Cidade cid2 = new Cidade(null, "Campinas", est2);
		est2.getCidades().addAll(Arrays.asList(cid2));
		
		estadoRepository.saveAll(Arrays.asList(est1,est2));
		cidadeRepository.saveAll(Arrays.asList(cid1,cid2));
		
				
		
	}
	
	


}
