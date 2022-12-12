package devrabaioli.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import devrabaioli.domain.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

}
