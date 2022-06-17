package prodigy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import prodigy.model.PreventiviModel;
import prodigy.repository.PreventiviRepository;

@Service
public class PreventiviService {

    @Autowired
    PreventiviRepository preventiviRepo;

    public PreventiviModel newPreventivo(PreventiviModel p) {
	return preventiviRepo.save(p);
    }

    public PreventiviModel updatePreventivo(Long id, PreventiviModel p) {

	PreventiviModel preventivoDaModificare = preventiviRepo.getReferenceById(id);

	preventivoDaModificare.setDescrizione(p.getDescrizione());
	preventivoDaModificare.setPrezzo(p.getPrezzo());
	preventivoDaModificare.setIdCliente(p.getIdCliente());

	return preventiviRepo.save(preventivoDaModificare);
    }

    public void deletePreventivoi(Long id) {

	PreventiviModel preventivoDaEliminare = preventiviRepo.getReferenceById(id);
	preventiviRepo.delete(preventivoDaEliminare);
    }
}
