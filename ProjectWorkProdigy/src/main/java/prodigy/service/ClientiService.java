package prodigy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import prodigy.model.ClientiModel;
import prodigy.repository.ClientiRepository;

@Service
public class ClientiService {

    @Autowired
    ClientiRepository clientiRepo;

    public ClientiModel newCliente(ClientiModel c) {
	return clientiRepo.save(c);
    }

    public ClientiModel updateCliente(Long id, ClientiModel c) {

	ClientiModel clienteDaModificare = clientiRepo.getReferenceById(id);
	clienteDaModificare.setNome(c.getNome());
	clienteDaModificare.setCognome(c.getCognome());
	clienteDaModificare.setEmail(c.getEmail());
	clienteDaModificare.setTelefono(c.getTelefono());

	return clientiRepo.save(clienteDaModificare);
    }

    public void deleteCliente(Long id) {
	ClientiModel clienteDaEliminare = clientiRepo.getReferenceById(id);
	clientiRepo.delete(clienteDaEliminare);
    }
}
