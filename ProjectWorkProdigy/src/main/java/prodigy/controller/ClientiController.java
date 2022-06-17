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

import prodigy.model.ClientiModel;
import prodigy.repository.ClientiRepository;
import prodigy.service.ClientiService;
import prodigy.utility.ExcelGeneratorClienti;
import prodigy.utility.PdfGeneratorClienti;

@RestController
public class ClientiController {

    @Autowired
    ClientiRepository clientiRepo;

    @Autowired
    ClientiService clientiService;

    @PostMapping("/clienti")
    public ClientiModel newCliente(@RequestBody ClientiModel c) {
	return clientiService.newCliente(c);
    }

    @GetMapping("/clienti")
    public List<ClientiModel> listaClienti() {
	return clientiRepo.findAll();
    }

    @PutMapping("/clienti/{id}")
    public ClientiModel updateCliente(@PathVariable("id") Long id, @RequestBody ClientiModel c) {
	return clientiService.updateCliente(id, c);

    }

    @DeleteMapping("/clienti/{id}")
    public void deleteCliente(@PathVariable("id") Long id) {
	clientiService.deleteCliente(id);
    }

    @GetMapping(value = "/download/Clienti.xlsx")
    public ResponseEntity<InputStreamResource> excelClienti() throws IOException {

	List<ClientiModel> clienti = clientiRepo.findAll();

	ByteArrayInputStream in = ExcelGeneratorClienti.clientiExcel(clienti);

	HttpHeaders headers = new HttpHeaders();
	headers.add("Content-Disposition", "attachment; filename=Clienti.xlsx");

	return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
    }

    @RequestMapping(value = "/download/Cliente.pdf", method = RequestMethod.GET, produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> pdfCliente() {

	List<ClientiModel> clienti = clientiRepo.findAll();
	ByteArrayInputStream bis = PdfGeneratorClienti.pdfClienti(clienti);

	HttpHeaders headers = new HttpHeaders();
	headers.add("Content-Disposition", "inline; filename=Clienti.pdf");

	return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
		.body(new InputStreamResource(bis));
    }

}
