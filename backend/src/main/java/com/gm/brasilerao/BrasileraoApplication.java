package com.gm.brasilerao;

import com.gm.brasilerao.models.Pokemon;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestClient;

@SpringBootApplication
public class BrasileraoApplication {



	public static void main(String[] args) {
		SpringApplication.run(BrasileraoApplication.class, args);
		RestClient restClient = RestClient.create();

		Pokemon result = restClient.get()
				.uri("https://pokeapi.co/api/v2/pokemon/charizard")
				.retrieve()
				.body(Pokemon.class);
		System.out.println("Nome: " + result.name +
				" Tipo: " + result.types +
				" ordem: " + result.order);
	}

}
