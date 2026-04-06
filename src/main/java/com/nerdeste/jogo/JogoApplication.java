package com.nerdeste.jogo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class JogoApplication {

	public static void main(String[] args) {
		SpringApplication.run(JogoApplication.class, args);
	}

}


@RestController
class TesteController {
    
    @GetMapping("/api/teste")
    public String teste() {
        return "BACKEND FUNCIONANDO!";
    }
}

