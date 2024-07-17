package com.alura.literalura.controller;

import com.alura.literalura.view.Main;
import lombok.Getter;
import lombok.Setter;

import java.util.Scanner;

@Getter
@Setter
public class MainMenuController {

    private Scanner sc = new Scanner(System.in);
    private Main main = new Main();
    private ValidateMenuOptions validateMenuOptions = new ValidateMenuOptions();
    private Integer option;

    public void showMainMenu() {
        String option = "";
        do {
            System.out.println(main.getMainMenu());
            System.out.print("Ingrese la opción deseada: ");
            validateMenuOptions.setOptionInteger(sc.nextLine());
            if (validateMenuOptions.validateIntegerOption())
                chooseOption(validateMenuOptions.getOptionInteger());
        } while (!option.equals("0"));
    }

    private void chooseOption(Integer option) {
        this.option = option;
        MenuOptionsProcessing processing = new MenuOptionsProcessing();
        processing.processMenuOption(option);


        /*switch (validateMenuOptions.getOptionInteger()) {
            case 1:
                processing.processMenuOption();
                break;
            case 0:
                System.out.println("Hasta pronto");
                sc.close();
                System.exit(0);
                break;
            default:
                System.out.println("Opción no válida\n");
        }*/
    }
}
