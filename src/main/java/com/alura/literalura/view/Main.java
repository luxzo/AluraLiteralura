package com.alura.literalura.view;

import com.alura.literalura.controller.MainMenuController;

public class Main {
    private String mainMenu = """
            1. Buscar libro por título
            2. Listar libros registrados
            3. Listar autores registrados
            4. Listar autores vivos en un determinado año
            5. Listar libros por idioma
            0. Salir
            """;

    private String bookLanguage = """
            Ingrese el idioma de libros para buscar:
            es - Español
            en - Inglés
            """;

    public void showMainMenu() {
        System.out.println("\n***********************************************");
        System.out.println("*********** Bienvenido a LiterAlura ***********");
        MainMenuController mainMenuController = new MainMenuController();
        mainMenuController.showMainMenu();
    }

    public String getMainMenu() {
        return mainMenu;
    }

    public String getBookLanguage() { return bookLanguage; }
}
