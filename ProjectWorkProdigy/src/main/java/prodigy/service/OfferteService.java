package prodigy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import prodigy.model.OfferteModel;
import prodigy.repository.OfferteRepository;

@Service
public class OfferteService {

    @Autowired
    OfferteRepository offerteRepo;

    public OfferteModel newOfferta(OfferteModel o) {
	return offerteRepo.save(o);
    }

    public OfferteModel updateOffera(Long id, OfferteModel o) {

	OfferteModel offertaDaModificare = offerteRepo.getReferenceById(id);

	offertaDaModificare.setDescrizione(o.getDescrizione());
	offertaDaModificare.setPrezzo(o.getPrezzo());
	offertaDaModificare.setDataAttivazione(o.getDataAttivazione());
	offertaDaModificare.setIdCliente(o.getIdCliente());

	return offerteRepo.save(offertaDaModificare);
    }

    public void deleteOfferta(Long id) {

	OfferteModel offertaDaEliminare = offerteRepo.getReferenceById(id);
	offerteRepo.delete(offertaDaEliminare);
    }
}
