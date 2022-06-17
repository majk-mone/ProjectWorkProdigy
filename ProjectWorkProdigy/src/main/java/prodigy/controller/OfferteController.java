package prodigy.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import prodigy.model.OfferteModel;
import prodigy.repository.OfferteRepository;
import prodigy.service.OfferteService;
import prodigy.utility.ExcelGeneratorOfferte;
import prodigy.utility.PdfGeneratorOfferte;

@RestController
public class OfferteController {

    @Autowired
    OfferteRepository offerteRepo;

    @Autowired
    OfferteService offerteService;

    @PostMapping("/offerte")
    public OfferteModel newOfferta(@RequestBody OfferteModel o) {
	return offerteService.newOfferta(o);
    }

    @GetMapping("/offerte")
    public List<OfferteModel> listaOfferte() {
	return offerteRepo.findAll();
    }

    @PutMapping("/offerte/{id}")
    public OfferteModel updateOfferta(@PathVariable("id") Long id, @RequestBody OfferteModel o) {
	return offerteService.updateOffera(id, o);

    }

    @DeleteMapping("/offerte/{id}")
    public void deleteOfferta(@PathVariable("id") Long id) {
	offerteService.deleteOfferta(id);
    }

    @GetMapping(value = "/download/Offerte.xlsx")
    public ResponseEntity<InputStreamResource> excelOfferte() throws IOException {

	List<OfferteModel> offerte = offerteRepo.findAll();

	ByteArrayInputStream in = ExcelGeneratorOfferte.offerteExcel(offerte);

	HttpHeaders headers = new HttpHeaders();
	headers.add("Content-Disposition", "attachment; filename=Offerte.xlsx");

	return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
    }

    @RequestMapping(value = "/download/Offerte.pdf", method = RequestMethod.GET, produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> pdfOfferte() {

	List<OfferteModel> offerte = offerteRepo.findAll();
	ByteArrayInputStream bis = PdfGeneratorOfferte.pdfOfferte(offerte);

	HttpHeaders headers = new HttpHeaders();
	headers.add("Content-Disposition", "inline; filename=Offerte.pdf");

	return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
		.body(new InputStreamResource(bis));
    }

}
