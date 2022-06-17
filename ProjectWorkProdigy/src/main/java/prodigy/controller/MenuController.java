package prodigy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MenuController {

    @GetMapping("/menu")
    public String menu() {
	return "menu";
    }

    @GetMapping("/clientiz")
    public String inviaCliente() {
	return "clienti";
    }

    @GetMapping("/offertez")
    public String inviaOfferta() {
	return "offerte";
    }

    @GetMapping("/preventiviz")
    public String inviaPreventivo() {
	return "preventivi";
    }

}
