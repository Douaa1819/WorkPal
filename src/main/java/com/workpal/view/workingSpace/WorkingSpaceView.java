package com.workpal.view.workingSpace;

import com.workpal.models.WorkingSpace;
import com.workpal.services.Interfaces.WorkingSpaceService;

import java.util.Scanner;

public class WorkingSpaceView {
    private WorkingSpaceService workingSpaceService;
    private Scanner scanner = new Scanner(System.in);

    public WorkingSpaceView(WorkingSpaceService workingSpaceService) {
        this.workingSpaceService = workingSpaceService;
    }

    public void addWorkingSpace() {
        System.out.print("Nom de l'espace de travail: ");
        String name = scanner.nextLine();
        System.out.print("Description de l'espace de travail: ");
        String description = scanner.nextLine();
        System.out.print("ID du manager (Organisateur): ");
        int managerId = scanner.nextInt();
        scanner.nextLine(); // Nettoyer le buffer

        WorkingSpace workingSpace = new WorkingSpace(0, name, description, managerId);
        workingSpaceService.createWorkingSpace(workingSpace);
        System.out.println("Espace de travail créé avec succès !");
    }

    public void showWorkingSpaces() {
        for (WorkingSpace ws : workingSpaceService.getAllWorkingSpaces()) {
            System.out.println("WorkingSpaceID: " + ws.getId() + " | Nom: " + ws.getName() +  " | Description: " + ws.getDescription() + " | ManagerID: " + ws.getManagerId());
        }
    }

    public void deleteWorkingSpace() {
        System.out.print("ID de l'espace de travail à supprimer: ");
        int id = scanner.nextInt();
        workingSpaceService.removeWorkingSpace(id);
        System.out.println("Espace de travail supprimé avec succès !");
    }


    public void updateWorkingSpace() {
        System.out.print("ID de l'espace de travail à mettre à jour: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        WorkingSpace workingSpace = workingSpaceService.getWorkingSpace(id);
        if (workingSpace != null) {
            System.out.print("Nouveau nom de l'espace de travail (laisser vide pour conserver l'ancien): ");
            String name = scanner.nextLine();
            System.out.print("Nouvelle description (laisser vide pour conserver l'ancienne): ");
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

}
