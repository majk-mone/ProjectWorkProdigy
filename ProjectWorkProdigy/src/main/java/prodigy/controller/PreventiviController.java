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

import prodigy.model.PreventiviModel;
import prodigy.repository.PreventiviRepository;
import prodigy.service.PreventiviService;
import prodigy.utility.ExcelGeneratorPreventivi;
import prodigy.utility.PdfGeneratorPreventivi;

@RestController
public class PreventiviController {

    @Autowired
    PreventiviRepository preventiviRepo;
    @Autowired
    PreventiviService preventiviService;

    @PostMapping("/preventivi")
    public PreventiviModel newPreventivo(@RequestBody PreventiviModel p) {
	return preventiviService.newPreventivo(p);
    }

    @GetMapping("/preventivi")
    public List<PreventiviModel> listaPreventivi() {
	return preventiviRepo.findAll();
    }

    @PutMapping("/preventivi/{id}")
    public PreventiviModel updatePreventivo(@PathVariable("id") Long id, @RequestBody PreventiviModel p) {
	return preventiviService.updatePreventivo(id, p);
    }

    @DeleteMapping("/preventivi/{id}")
    public void deletePreventivo(@PathVariable("id") Long id) {
	preventiviService.deletePreventivoi(id);
    }

    @GetMapping(value = "/download/Preventivi.xlsx")
    public ResponseEntity<InputStreamResource> excelPreventivi() throws IOException {
	List<PreventiviModel> preventivi = preventiviRepo.findAll();
	ByteArrayInputStream in = ExcelGeneratorPreventivi.preventiviExcel(preventivi);
	HttpHeaders headers = new HttpHeaders();
	headers.add("Content-Disposition", "attachment; filename=Preventivi.xlsx");

	return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
    }

    @RequestMapping(value = "/download/Preventivi.pdf", method = RequestMethod.GET, produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> pdfPreventivi() {
	List<PreventiviModel> preventivi = preventiviRepo.findAll();
	ByteArrayInputStream bis = PdfGeneratorPreventivi.pdfPreventivi(preventivi);

	HttpHeaders headers = new HttpHeaders();
	headers.add("Content-Disposition", "inline; filename=Preventivi.pdf");

	return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
		.body(new InputStreamResource(bis));
    }
}
