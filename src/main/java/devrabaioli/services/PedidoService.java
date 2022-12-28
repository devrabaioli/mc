package devrabaioli.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import devrabaioli.domain.Pedido;
import devrabaioli.repositories.PedidoRepository;
import devrabaioli.services.exception.ObjectNotfoundException;



@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repo;
	
	public Pedido find(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotfoundException("Objeto não encontrado id: " + id + " Tipo: " +
		Pedido.class.getName()));
	}


}
