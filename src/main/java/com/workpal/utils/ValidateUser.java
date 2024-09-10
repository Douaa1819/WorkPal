package com.workpal.utils;

import com.workpal.exceptions.InvalidInputException;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateUser {


    public static void validateEmail(String email) throws InvalidInputException {
        if (email == null || !email.contains("@")) {
            throw new InvalidInputException("Email invalide.");
        }
    }

    public static void validatePassword(String password) throws InvalidInputException {
        if (password == null || password.length() < 6) {
            throw new InvalidInputException("Le mot de passe doit contenir au moins 6 caractères.");
        }
    }

    public static void validateName(String name) throws InvalidInputException {
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidInputException("Le nom ne peut pas être vide.");
        }
    }

    public static void validatePhone(String phone) throws InvalidInputException {
        if (phone == null || phone.trim().isEmpty()) {
            throw new InvalidInputException("Le numéro de téléphone ne peut pas être vide.");
        }

        // Vérifie si le numéro commence par un '+' ou un chiffre, puis contient uniquement des chiffres
        if (!phone.matches("^\\+?\\d+$")) {
            throw new InvalidInputException("Le numéro de téléphone doit contenir uniquement des chiffres et peut éventuellement commencer par un '+'");
        }
    }

    public static int getIntInput(Scanner scanner) {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException ex) {
                System.out.print("Entrée invalide Veuillez saisir un nombre entier: ");
            }
        }
    }
}
