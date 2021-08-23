package uh.ac.cr;

import uh.ac.cr.managers.UserManager;
import uh.ac.cr.models.Supervisor;
import uh.ac.cr.models.User;

import java.text.ParseException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws ParseException {
	// write your code here

        UserManager userManager = new UserManager();

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
                        //TODO - Implement getMenu().
                        user.getMenu();
                    } else {
                        System.out.println("ERROR: Usuario o contraseña incorrecta.");
                    }

                    break;
                case 2:
                    //2: Register.
                    //TODO - Add if the user ID exits already.
                    System.out.println("ID del usuario: ");
                    int newUserID = scanner.nextInt();
                    scanner.nextLine();
                    //TODO - Add if the username exits already.
                    System.out.println("Nombre de usuario: ");
                    String newUsername = scanner.nextLine();

                    System.out.println("Contraseña: ");
                    String newPassword = scanner.nextLine();

                    userManager.createUser(newUserID, newUsername, newPassword);
                    userManager.getUser();

                    break;
                case 3:
                    break;
            }
        }
    }
}
