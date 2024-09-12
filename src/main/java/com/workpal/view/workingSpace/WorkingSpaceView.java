package com.workpal.view.workingSpace;

import com.workpal.models.WorkingSpace;
import com.workpal.services.Interfaces.WorkingSpaceService;

import java.util.List;
import java.util.Scanner;

public class WorkingSpaceView {

    private final WorkingSpaceService workingSpaceService;
    private final Scanner scanner;

    public WorkingSpaceView(WorkingSpaceService workingSpaceService) {
        this.workingSpaceService = workingSpaceService;
        this.scanner = new Scanner(System.in);
    }

    public void displayWorkingSpaceMenu() {
        while (true) {
            System.out.println("=== Gestion des Working Spaces ===");
            System.out.println("1. Créer un Working Space");
            System.out.println("2. Afficher tous les Working Spaces");
            System.out.println("3. Modifier un Working Space");
            System.out.println("4. Supprimer un Working Space");
            System.out.println("5. Quitter");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consommer la nouvelle ligne

            switch (choice) {
                case 1:
                    createWorkingSpace();
                    break;
                case 2:
                    listAllWorkingSpaces();
                    break;
                case 3:
                    updateWorkingSpace();
                    break;
                case 4:
                    deleteWorkingSpace();
                    break;
                case 5:
                    System.out.println("Sortie du programme.");
                    return;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
            }
        }
    }

    private void createWorkingSpace() {
        String name;
        String description;

        do {
            System.out.println("Entrez le nom du Working Space:");
            name = scanner.nextLine();
            if (name.isEmpty()) {
                System.out.println("Le nom ne peut pas être vide. Veuillez réessayer.");
            }
        } while (name.isEmpty());
        do {
            System.out.println("Entrez la description du Working Space:");
            description = scanner.nextLine();
            if (description.isEmpty()) {
                System.out.println("La description ne peut pas être vide. Veuillez réessayer.");
            }
        } while (description.isEmpty());
        WorkingSpace workingSpace = new WorkingSpace(0, name, description, 1); // '1' est un exemple d'ID du gestionnaire
        workingSpaceService.createWorkingSpace(workingSpace);
        System.out.println("Working Space créé avec succès !");
    }


    private void listAllWorkingSpaces() {
        List<WorkingSpace> workingSpaces = workingSpaceService.getAllWorkingSpaces();
        if (workingSpaces.isEmpty()) {
            System.out.println("Aucun Working Space trouvé.");
        } else {
            System.out.println("Liste des Working Spaces:");
            for (WorkingSpace ws : workingSpaces) {
                System.out.println("ID: " + ws.getId() + ", Nom: " + ws.getName() + ", Description: " + ws.getDescription());
            }
        }
    }

    private void updateWorkingSpace() {
        List<WorkingSpace> workingSpaces = workingSpaceService.getAllWorkingSpaces();

        if (workingSpaces.isEmpty()) {
            System.out.println("Aucun Working Space disponible pour modification.");
            return;
        }

        // Afficher la liste des WorkingSpaces disponibles
        System.out.println("Voici la liste des Working Spaces disponibles :");
        for (int i = 0; i < workingSpaces.size(); i++) {
            WorkingSpace ws = workingSpaces.get(i);
            System.out.println((i + 1) + ". " + ws.getName() + " (ID: " + ws.getId() + ")");
        }

        int choice;
        do {
            System.out.println("Entrez le numéro du Working Space à modifier:");
            choice = scanner.nextInt();
            if (choice < 1 || choice > workingSpaces.size()) {
                System.out.println("Choix invalide. Veuillez choisir un numéro dans la liste.");
            }
        } while (choice < 1 || choice > workingSpaces.size());

        WorkingSpace selectedWorkingSpace = workingSpaces.get(choice - 1);

        // Demander les nouvelles informations
        scanner.nextLine();
        System.out.println("Entrez le nouveau nom du Working Space (actuel: " + selectedWorkingSpace.getName() + "):");
        String newName = scanner.nextLine();

        System.out.println("Entrez la nouvelle description du Working Space (actuelle: " + selectedWorkingSpace.getDescription() + "):");
        String newDescription = scanner.nextLine();

        selectedWorkingSpace.setName(newName);
        selectedWorkingSpace.setDescription(newDescription);

        workingSpaceService.updateWorkingSpace(selectedWorkingSpace);
        System.out.println("Working Space '" + selectedWorkingSpace.getName() + "' mis à jour avec succès !");
    }


    private void deleteWorkingSpace() {
        List<WorkingSpace> workingSpaces = workingSpaceService.getAllWorkingSpaces();

        if (workingSpaces.isEmpty()) {
            System.out.println("Aucun Working Space disponible pour suppression.");
            return;
        }

        System.out.println("Voici la liste des Working Spaces disponibles :");
        for (int i = 0; i < workingSpaces.size(); i++) {
            WorkingSpace ws = workingSpaces.get(i);
            System.out.println((i + 1) + ". " + ws.getName() + " (ID: " + ws.getId() + ")");
        }

        int choice;
        do {
            System.out.println("Entrez le numéro du Working Space à supprimer:");
            choice = scanner.nextInt();
            if (choice < 1 || choice > workingSpaces.size()) {
                System.out.println("Choix invalide. Veuillez choisir un numéro dans la liste.");
            }
        } while (choice < 1 || choice > workingSpaces.size());

        WorkingSpace selectedWorkingSpace = workingSpaces.get(choice - 1);
        workingSpaceService.deleteWorkingSpace(selectedWorkingSpace.getId());
        System.out.println("Working Space '" + selectedWorkingSpace.getName() + "' supprimé avec succès !");
    }


}