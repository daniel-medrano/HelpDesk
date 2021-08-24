package uh.ac.cr.models;

import uh.ac.cr.managers.TicketManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Menu {
    //Objects declaration.
    TicketManager ticketManager;
    Scanner scanner;
    //Constructor.
    public Menu() {
        ticketManager = new TicketManager();
        scanner = new Scanner(System.in);
    }
    //User's menu.
    public void userMenu(User user) {
        int option = -1;

        //Menu
        while (option != 3) {
            System.out.println("1: Crear tiquete.\n" +
                    "2: Ver el estado de mis tiquetes.\n" +
                    "3: Cerrar sesión.");
            option = scanner.nextInt();
            scanner.nextLine();
            switch (option) {
                case 1:
                    System.out.println("ID del tiquete.");
                    int ticketID = scanner.nextInt();
                    scanner.nextLine();
                    //TODO - No more than 50 characters.
                    System.out.println("Descripción del problema.");
                    String problemDescription = scanner.nextLine();

                    Date date = new Date();

                    System.out.println("Fecha limite para resolver el tiquete. En el formato dd/mm/yyyy.");
                    String dateFormat = scanner.next();
                    scanner.nextLine();
                    Date deadlineExpected = null;
                    try {
                        deadlineExpected = new SimpleDateFormat("dd/MM/yyyy").parse(dateFormat);
                    } catch (ParseException e) {
                        System.out.println("ERROR: Formato incorrecto.");
                    }

                    createTicket(ticketID, problemDescription, date, deadlineExpected, user);
                    System.out.println("\n¡TIQUETE CREADO!\n");
                    break;
                case 2:
                    System.out.println("TIQUETES ACTIVOS");
                    System.out.println(ticketManager.readUserUnfinishedTickets(user));
                    System.out.println("TIQUETES TERMINADOS");
                    System.out.println(ticketManager.readUserFinishedTickets(user));

                    break;
                case 3:
                    break;
            }
        }

    }
    //Supervisor's menu.
    public void supervisorMenu(Supervisor supervisor) {
        System.out.println("\nSUPERVISOR\n");
        int option = -1;

        //Menu
        while (option != 3) {
            System.out.println("1: Crear tiquete.\n" +
                    "2: Ver el estado de mis tiquetes.\n" +
                    "3: Cerrar sesión.");
            option = scanner.nextInt();
            scanner.nextLine();
            switch (option) {
                case 1:
                    break;
                case 2:

                    break;
                case 3:
                    break;
            }
        }
    }
    //Supporter's menu.
    public void supporterMenu(Supporter supporter) {
        System.out.println("\nSOPORTISTA\n");
        int option = -1;

        //Menu
        while (option != 4) {
            System.out.println("1: Autoasignarse un tiquete.\n" +
                    "2: Comentar tiquete.\n" +
                    "3: Terminar un tiquete.\n" +
                    "4: Cerrar sesión.");
            option = scanner.nextInt();
            scanner.nextLine();
            switch (option) {
                case 1:
                    //The ID of the ticket is asked.
                    System.out.println("Seleccione el tiquete con su ID.");
                    System.out.println(ticketManager.readAvailableTickets());
                    int ticketID = scanner.nextInt();
                    scanner.nextLine();
                    //The ticket is assigned to the supporter.
                    try {
                        takeTicket(ticketID, supporter);
                        System.out.println("\n¡TIQUETE TOMADO!\n");

                    } catch (NullPointerException e) {
                        System.out.println("\nERROR: El ID introducido no existe.\n");
                    }
                    break;
                case 2:
                    //TODO ----------------
                    System.out.println("Seleccione el tiquete con su ID.");
                    System.out.println();
                    ticketID = scanner.nextInt();
                    scanner.nextLine();


                    break;
                case 3:
                    System.out.println("Seleccione el tiquete con su ID.");
                    System.out.println(ticketManager.readSupporterUnfinishedTickets(supporter));
                    ticketID = scanner.nextInt();
                    scanner.nextLine();

                    ticketManager.finishTicket(ticketID);
                    break;
                case 4:
                    break;
            }
        }
    }

    public void adminMenu() {

    }

    public void createTicket(int ticketID, String problemDescription, Date date, Date deadlineExpected, User requesterOfTicket) {
        ticketManager.createTicket(ticketID, requesterOfTicket, problemDescription, date, deadlineExpected);
    }

    public void takeTicket(int ticketID, Supporter supporter) throws NullPointerException {
        ticketManager.assignTicket(ticketID, supporter);
    }
}
