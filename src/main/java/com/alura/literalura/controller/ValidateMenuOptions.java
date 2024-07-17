package com.alura.literalura.controller;

public class ValidateMenuOptions {
    private Integer optionInteger;
    public boolean validateIntegerOption() {
        if (optionInteger instanceof Integer)
            return true;
        return false;
    }

    public int getOptionInteger() {
        return optionInteger;
    }

    public void setOptionInteger(String option) {
        try {
            this.optionInteger = Integer.valueOf(option);
        } catch (NumberFormatException e) {
            System.out.println("Ingrese solo números, intente nuevamente\n");
        }
    }
}
