package com.alura.literalura.controller;

import com.alura.literalura.model.Author;
import com.alura.literalura.model.Book;
import com.alura.literalura.model.BookDataDTO;
import com.alura.literalura.model.ResultDataDTO;
import com.alura.literalura.repository.IAuthorRepository;
import com.alura.literalura.repository.IBookRepository;
import com.alura.literalura.service.ConsumeAPI;
import com.alura.literalura.service.ConvertData;
import com.alura.literalura.view.Main;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;
import java.util.Scanner;

public class MenuOptionsProcessing {
    private Scanner sc = new Scanner(System.in);
    private ValidateMenuOptions validateMenuOptions = new ValidateMenuOptions();
    private final String BASE_URL = "https://gutendex.com/books/?search=";
    private ConsumeAPI consumeApi = new ConsumeAPI();
    private ConvertData conversor = new ConvertData();
    private IBookRepository bookRepository;
    private IAuthorRepository authorRepository;

//    public void processMenuOption() {
//        searchBook();
//    }

    public void processMenuOption(Integer option) {
        switch (option) {
            case 1:
                searchBook();
                break;
            case 2:
                listBooks();
                break;
            case 3:
                listAuthors();
                break;
            case 4:
                listAliveAuthors();
                break;
            case 5:
                listBooksByLanguage();
            case 0:
                System.out.println("Hasta pronto");
                sc.close();
                System.exit(0);
                break;
            default:
                System.out.println("Opción no válida\n");
        }
    }

    private void listBooksByLanguage() {
        Main main = new Main();
        System.out.println(main.getBookLanguage());
        String option = sc.nextLine();
        try {
            if (option.equalsIgnoreCase("es") || option.equalsIgnoreCase("en")) {

                List<Book> languageBook = bookRepository.findByLanguageIgnoreCase(option);
                if (languageBook.isEmpty())
                    System.out.println("No hay libros registrados para el idioma ingresado");
                else
                    languageBook.forEach(System.out::println);
            } else
                System.out.println("Opción no válida");
        } catch (NullPointerException e) {
            System.out.println("\nLa lista está vacía en la base de datos\n");
        }
        main.showMainMenu();
    }

    private void listAliveAuthors() {
        System.out.println("Ingrese el año para buscar un autor: ");
        Integer year = sc.nextInt();
        try {
            List<Author> aliveAuthors = authorRepository.searchAliveAuthorsByYear(year);
            if (aliveAuthors.isEmpty())
                System.out.println("No se encontraron autores vivos para el año ingresado");
            else
                aliveAuthors.forEach(System.out::println);
        } catch (NullPointerException e) {
            System.out.println("\nLa lista está vacía en la base de datos\n");
        }
    }

    private void listAuthors() {
        try {
            List<Author> authors = authorRepository.findAll();
            authors.forEach(System.out::println);
        } catch (NullPointerException e) {
            System.out.println("\nLa lista está vacía en la base de datos\n");
        }
    }

    private void listBooks() {
        try {
            List<Book> books = bookRepository.findAll();
            books.forEach(System.out::println);
        } catch (NullPointerException e) {
            System.out.println("\nLa lista está vacía en la base de datos\n");
        }
    }

    private void searchBook() {
        BookDataDTO bookDataDTO = getBookDataFromApi();
        Book book = new Book(bookDataDTO);
        try {
            Author author = authorRepository.findByName(bookDataDTO.authors().get(0).name());
            System.out.println(author);
            if (author != null) {
                book.addAuthor(author);
                book.setAuthor(author);
            }
            else
                authorRepository.save(book.getAuthor());
            bookRepository.save(book);
            System.out.println(book);
        } catch (DataIntegrityViolationException e) {
            System.out.println("Este libro ya fue registrado previamente");
        } catch (NullPointerException e) {
            System.out.println("No se encontró ningún libro\n");
        }
    }

    private BookDataDTO getBookDataFromApi() {
        System.out.println("Ingresa el nombre de un libro para buscar: ");
        String bookTitle = sc.nextLine();
        String json = consumeApi.getData(BASE_URL + bookTitle.replace(" ", "%20"));
        ResultDataDTO resultDataDTO = conversor.getData(json, ResultDataDTO.class);
        return resultDataDTO.books().get(0);
    }
}
