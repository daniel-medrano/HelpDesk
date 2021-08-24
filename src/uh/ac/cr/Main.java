package uh.ac.cr;

import uh.ac.cr.managers.UserManager;
import uh.ac.cr.models.Menu;
import uh.ac.cr.models.Supervisor;
import uh.ac.cr.models.Supporter;
import uh.ac.cr.models.User;

import java.text.ParseException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here

        UserManager userManager = new UserManager();
        Menu menu = new Menu(userManager);

        Scanner scanner = new Scanner(System.in);
        int option = -1;

        //Menu
        while (option != 3) {
            System.out.println("1: Iniciar sesión.\n" +
                    "2: Registrarse.\n" +
                    "3: Salir.");
            option = scanner.nextInt();
            scanner.nextLine();
            switch (option) {
                case 1:
                    //1: Login.
                    System.out.println("Nombre de usuario: ");
                    String username = scanner.nextLine();

                    System.out.println("Contraseña: ");
                    String password = scanner.nextLine();

                    User user = userManager.validateUser(username, password);

                    if (user != null) {
                        if (user instanceof Supporter) {
                            menu.supporterMenu((Supporter) user);
                        } else if (user instanceof Supervisor) {
                            menu.supervisorMenu((Supervisor) user);
                        } else {
                            menu.userMenu(user);
                        }
                    } else {
                        System.out.println("\nERROR: Usuario o contraseña incorrecta.\n");
                    }

                    break;
                case 2:
                    //2: Register.
                    //TODO - Add if the user ID exits already.
                    System.out.println("ID del usuario:");
                    int newUserID = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Nombre:");
                    String newFirstName = scanner.nextLine();

                    System.out.println("Apellido:");
                    String newSecondName = scanner.nextLine();

                    System.out.println("Departamento:");
                    String newDepartment = scanner.nextLine();
                    //TODO - Add if the username exits already.
                    System.out.println("Nombre de usuario:");
                    String newUsername = scanner.nextLine();

                    System.out.println("Contraseña: ");
                    String newPassword = scanner.nextLine();

                    //---------------------------------------------------------------------------------------------
                    System.out.println("1 para usuario, 2 para soportista, 3 para supervisor.");
                    int role = scanner.nextInt();
                    scanner.nextLine();

                    if (role == 1) {
                        userManager.createUser(newUserID,newFirstName, newSecondName, newDepartment, newUsername, newPassword);
                    } else if (role == 2) {
                        userManager.createSupporter(newUserID,newFirstName, newSecondName, newDepartment, newUsername, newPassword);
                    } else if (role == 3) {
                        userManager.createSupervisor(newUserID,newFirstName, newSecondName, newDepartment, newUsername, newPassword);
                    } else {
                        System.out.println("ERROR");
                    }
                    break;
                case 3:
                    break;
            }
        }
    }
}
