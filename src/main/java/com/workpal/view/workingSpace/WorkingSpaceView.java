package com.workpal.view.workingSpace;

import com.workpal.dao.impl.FavorisDAOImpl;
import com.workpal.models.Favoris;
import com.workpal.models.WorkingSpace;
import com.workpal.services.Interfaces.WorkingSpaceService;
import com.workpal.services.Interfaces.FavorisService;

import java.util.List;
import java.util.Scanner;

public class WorkingSpaceView {
    private final WorkingSpaceService workingSpaceService;
    private final FavorisService favorisService;
    private final Scanner scanner;
    private final int membreId;

    public WorkingSpaceView(WorkingSpaceService workingSpaceService, FavorisService favorisService, int membreId) {
        this.workingSpaceService = workingSpaceService;
        this.favorisService = favorisService;
        this.scanner = new Scanner(System.in);
        this.membreId = membreId;
    }
    public WorkingSpaceView(WorkingSpaceService workingSpaceService) {
        this(workingSpaceService, null, 0);
    }

    public void displayWorkingSpaceMenu() {
        while (true) {
            System.out.println("=== Gestion des Working Spaces ===");
            System.out.println("1. Créer un Working Space");
            System.out.println("2. Afficher tous les Working Spaces");
            System.out.println("3. Modifier un Working Space");
            System.out.println("4. Supprimer un Working Space");
            System.out.println("5. Ajouter un Working Space aux favoris");
            System.out.println("6. Afficher vos favoris");
            System.out.println("7. Supprimer un favori");
            System.out.println("8. Quitter");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Nettoyer le buffer
            switch (choice) {
                case 1:
                    addWorkingSpace();
                    break;
                case 2:
                    showWorkingSpaces();
                    break;
                case 3:
                    updateWorkingSpace();
                    break;
                case 4:
                    deleteWorkingSpace();
                    break;
                case 5:
                    addFavoris();
                    break;
                case 6:
                    listFavoris();
                    break;
                case 7:
                    removeFavoris();
                    break;
                case 8:
                    System.out.println("Sortie du programme.");
                    return;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
            }
        }
    }

    // Ajouter un nouvel espace de travail
    public void addWorkingSpace() {
        System.out.print("Nom de l'espace de travail : ");
        String name = scanner.nextLine();
        System.out.print("Description de l'espace de travail : ");
        String description = scanner.nextLine();
        System.out.print("ID du manager (Organisateur) : ");
        int managerId = scanner.nextInt();
        scanner.nextLine(); // Nettoyer le buffer

        WorkingSpace workingSpace = new WorkingSpace(0, name, description, managerId);
        workingSpaceService.createWorkingSpace(workingSpace);
        System.out.println("Espace de travail créé avec succès !");
    }

    // Afficher tous les espaces de travail
    public void showWorkingSpaces() {
        List<WorkingSpace> workingSpaces = workingSpaceService.getAllWorkingSpaces();
        System.out.println("Liste des espaces de travail :");
        for (WorkingSpace ws : workingSpaces) {
            System.out.println("ID: " + ws.getId() + " | Nom: " + ws.getName() + " | Description: " + ws.getDescription() + " | Manager ID: " + ws.getManagerId());
        }
    }

    // Mettre à jour un espace de travail
    public void updateWorkingSpace() {
        System.out.print("ID de l'espace de travail à mettre à jour : ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Nettoyer le buffer

        WorkingSpace workingSpace = workingSpaceService.getWorkingSpace(id);
        if (workingSpace != null) {
            System.out.print("Nouveau nom de l'espace de travail (laisser vide pour conserver l'ancien) : ");
            String name = scanner.nextLine();
            System.out.print("Nouvelle description (laisser vide pour conserver l'ancienne) : ");
            String description = scanner.nextLine();

            if (!name.isEmpty()) {
                workingSpace.setName(name);
            }
            if (!description.isEmpty()) {
                workingSpace.setDescription(description);
            }

            int currentManagerId = workingSpace.getManagerId();
            workingSpace.setManagerId(currentManagerId);

            workingSpaceService.updateWorkingSpace(workingSpace);
            System.out.println("Espace de travail mis à jour avec succès !");
        } else {
            System.out.println("Espace de travail non trouvé.");
        }
    }

    // Supprimer un espace de travail
    public void deleteWorkingSpace() {
        System.out.print("ID de l'espace de travail à supprimer : ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Nettoyer le buffer
        workingSpaceService.removeWorkingSpace(id);
        System.out.println("Espace de travail supprimé avec succès !");
    }

    // Ajouter un favori
    public void addFavoris() {
        System.out.print("ID de l'espace de travail à ajouter aux favoris : ");
        int workingSpaceId = scanner.nextInt();
        scanner.nextLine(); // Nettoyer le buffer

        favorisService.ajouterFavori(membreId, workingSpaceId);
        System.out.println("Espace de travail ajouté aux favoris !");
    }

    // Afficher la liste des favoris
    public void listFavoris() {
        List<Favoris> favorisList = favorisService.obtenirFavorisParMembre(membreId);
        if (favorisList.isEmpty()) {
            System.out.println("Vous n'avez aucun favori.");
        } else {
            System.out.println("Vos favoris :");
            for (Favoris fav : favorisList) {
                System.out.println("Espace de travail ID: " + fav.getWorkingSpaceId());
            }
        }
    }

    // Supprimer un favori
    public void removeFavoris() {
        System.out.print("ID de l'espace de travail à supprimer des favoris : ");
        int workingSpaceId = scanner.nextInt();
        scanner.nextLine(); // Nettoyer le buffer

        favorisService.retirerFavori(membreId, workingSpaceId);
        System.out.println("Favori supprimé avec succès !");
    }
}
