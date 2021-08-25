package uh.ac.cr;

import uh.ac.cr.managers.TicketManager;
import uh.ac.cr.managers.UserManager;
import uh.ac.cr.models.Menu;
import uh.ac.cr.models.Supervisor;
import uh.ac.cr.models.Supporter;
import uh.ac.cr.models.User;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here

        //Student: José Daniel Medrano Guadamuz

        UserManager userManager = new UserManager();
        TicketManager ticketManager = new TicketManager();


        userManager.createUser(1,"Daniel", "Medrano", "IT", "Daniel123", "1234");
        userManager.createSupporter(2,"Ximena", "Solano", "IT", "Xime123", "1234");
        userManager.createSupervisor(3,"Juanito", "Mora", "IT", "Mora123", "1234");

        Menu menu = new Menu(userManager, ticketManager);

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
                    System.out.println("Inserte su nombre de usuario. Inserte \"Cancelar\" si no desea continuar. ");
                    String username = scanner.nextLine();
                    if (username.equals("Cancelar")) {
                        System.out.println("\nLa operación ha sido cancelada.\n");
                        //Exits the operation.
                        break;
                    }

                    System.out.println("Inserte la contraseña. Inserte \"Cancelar\" si no desea continuar.");
                    String password = scanner.nextLine();
                    if (password.equals("Cancelar")) {
                        System.out.println("\nLa operación ha sido cancelada.\n");
                        //Exits the operation.
                        break;
                    }

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
                    boolean ready = false;
                    boolean cancel = false;
                    int newUserID = 0;
                    String input;
                    do {
                        try {
                            System.out.println("Inserte el ID del usuario. Inserte \"Cancelar\" si no desea continuar.");
                            input = scanner.next();
                            scanner.nextLine();

                            if (input.equals("Cancelar")) {
                                System.out.println("\nLa operación ha sido cancelada.\n");
                                //Exits the operation.
                                cancel = true;
                                break;
                            } else {
                                newUserID = Integer.parseInt(input);
                                if (!userManager.existsUser(newUserID)) {
                                    ready = true;
                                } else {
                                    System.out.println("\nERROR: Ya existe un usuario con este ID.\n");
                                }
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("\nERROR: El ID debe ser un número.\n");
                        }
                    } while (!ready);
                    if (cancel) {
                        break;
                    }

                    System.out.println("Inserte su nombre. Inserte \"Cancelar\" si no desea continuar.");
                    String newFirstName = scanner.nextLine();
                    if (newFirstName.equals("Cancelar")) {
                        System.out.println("\nLa operación ha sido cancelada.\n");
                        //Exits the operation.
                        break;
                    }

                    System.out.println("Inserte su apellido. Inserte \"Cancelar\" si no desea continuar.");
                    String newSecondName = scanner.nextLine();
                    if (newSecondName.equals("Cancelar")) {
                        System.out.println("\nLa operación ha sido cancelada.\n");
                        //Exits the operation.
                        break;
                    }

                    System.out.println("Inserte el departamento al que pertenece. Inserte \"Cancelar\" si no desea continuar.");
                    String newDepartment = scanner.nextLine();
                    if (newDepartment.equals("Cancelar")) {
                        System.out.println("\nLa operación ha sido cancelada.\n");
                        //Exits the operation.
                        break;
                    }

                    System.out.println("Inserte su nombre de usuario. Inserte \"Cancelar\" si no desea continuar.");
                    String newUsername = scanner.nextLine();
                    if (newUsername.equals("Cancelar")) {
                        System.out.println("\nLa operación ha sido cancelada.\n");
                        //Exits the operation.
                        break;
                    }

                    System.out.println("Inserte la contraseña. Inserte \"Cancelar\" si no desea continuar. ");
                    String newPassword = scanner.nextLine();
                    if (newPassword.equals("Cancelar")) {
                        System.out.println("\nLa operación ha sido cancelada.\n");
                        //Exits the operation.
                        break;
                    }

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
