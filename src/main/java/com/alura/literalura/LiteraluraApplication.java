package com.alura.literalura;

import com.alura.literalura.repository.IAuthorRepository;
import com.alura.literalura.repository.IBookRepository;
import com.alura.literalura.view.Main;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

	@Autowired
	IBookRepository bookRepository;

	@Autowired
	IAuthorRepository authorRepository;

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Main main = new Main();
		main.showMainMenu();
	}
}
