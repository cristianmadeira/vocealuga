package br.cefetrj.mg.bsi.vocealuga.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/")
public class HomeController {

	@GetMapping
	public String index() {
		return "index";
	}
}
