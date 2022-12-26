package devrabaioli.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import devrabaioli.domain.Cliente;
import devrabaioli.repositories.ClienteRepository;
import devrabaioli.services.exception.ObjectNotfoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;
	
	public Cliente find(Integer cod) {
		Optional<Cliente> obj = repo.findById(cod);
		return obj.orElseThrow(() -> new ObjectNotfoundException(
				"Objeto nao encontrado Id: " + cod + " Tipo: " + Cliente.class.getName() ));
	}

}
