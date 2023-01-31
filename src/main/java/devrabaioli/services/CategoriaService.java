package devrabaioli.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import devrabaioli.domain.Categoria;
import devrabaioli.repositories.CategoriaRepository;
import devrabaioli.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	
	public Categoria find(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objecto nao encontrado: " + id + "da classe :" + Categoria.class.getName()));
	}

}
