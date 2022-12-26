package devrabaioli.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import devrabaioli.domain.Categoria;
import devrabaioli.repositories.CategoriaRepository;
import devrabaioli.services.exception.ObjectNotfoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;
	
	public Categoria find(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotfoundException("Objecto nao encontrado id: " + id + " Tipo: " + Categoria.class.getName())); 
	}
	
}
