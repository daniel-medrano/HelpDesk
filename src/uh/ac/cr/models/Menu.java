package uh.ac.cr.models;

import uh.ac.cr.managers.TicketManager;
import uh.ac.cr.managers.UserManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    //Objects declaration.
    TicketManager ticketManager;
    UserManager userManager;
    Scanner scanner;
    //Constructor.
    public Menu(UserManager userManager) {
        this.userManager = userManager;
        ticketManager = new TicketManager();
        scanner = new Scanner(System.in);
    }
    //User's menu.
    public void userMenu(User user) {
        int option = -1;

        //Menu
        while (option != 4) {
            System.out.println("1: Crear tiquete.\n" +
                    "2: Ver el estado de mis tiquetes.\n" +
                    "3: Consultar tiquete con detalle.\n" +
                    "4: Cerrar sesión.");
            option = scanner.nextInt();
            scanner.nextLine();
            switch (option) {
                case 1:
                    System.out.println("ID del tiquete.");
                    int ticketID = scanner.nextInt();
                    scanner.nextLine();

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
                    System.out.println("TIQUETES DE: " + user.getFullName());
                    System.out.println("TIQUETES TERMINADOS");
                    System.out.println(ticketManager.readUserFinishedTickets(user));
                    System.out.println("TIQUETES ACTIVOS");
                    System.out.println(ticketManager.readUserUnfinishedTickets(user));
                    break;
                case 3:
                    System.out.println("TIQUETES DE: " + user.getFullName());
                    System.out.println("TIQUETES TERMINADOS");
                    System.out.println(ticketManager.readUserFinishedTickets(user));
                    System.out.println("TIQUETES ACTIVOS");
                    System.out.println(ticketManager.readUserUnfinishedTickets(user));
                    System.out.println("Seleccione el tiquete a consultar con su ID:");
                    try {
                        ticketID = scanner.nextInt();
                        scanner.nextLine();

                        System.out.println(ticketManager.consultTicketForUsers(ticketID, user));
                    } catch (NullPointerException e) {
                        System.out.println("\nERROR: El ID introducido no corresponde a uno de la lista.\n");
                    } catch (InputMismatchException e) {
                        System.out.println("\nERROR: El ID debe ser un número.\n");
                    }
                    break;
                case 4:
                    break;
            }
        }
    }
    //Supervisor's menu.
    public void supervisorMenu(Supervisor supervisor) {
        System.out.println("\nSUPERVISOR\n");
        int option = -1;

        //Menu
        while (option != 5) {
            System.out.println("1: Asignar tiquete a soportista.\n" +
                    "2: Visualizar todos los tiquetes.\n" +
                    "3: Consultar tiquete con detalle.\n" +
                    "4: Estadísticas.\n" +
                    "5: Cerrar sesión.");
            option = scanner.nextInt();
            scanner.nextLine();
            switch (option) {
                case 1:
                    System.out.println("Seleccione el soportista con su ID.");
                    System.out.println(userManager.readSupporters());
                    //TODO - Verify if the ID exists.
                    int supporterID = scanner.nextInt();
                    scanner.nextLine();

                    Supporter supporter = (Supporter) userManager.getUser(supporterID);

                    System.out.println("Seleccione el tiquete con su ID.");
                    System.out.println(ticketManager.readAvailableTickets());
                    int ticketID = scanner.nextInt();
                    scanner.nextLine();

                    ticketManager.assignTicket(ticketID, supporter);
                    System.out.println("\n¡TIQUETE ASIGNADO!\n");
                    break;
                case 2:
                    System.out.println("TIQUETES PENDIENTES");
                    System.out.println(ticketManager.readHangingTickets());
                    System.out.println("TIQUETES TERMINADOS");
                    System.out.println(ticketManager.readFinishedTickets());
                    System.out.println("TIQUETES DISPONIBLES");
                    System.out.println(ticketManager.readAvailableTickets());
                    break;
                case 3:
                    System.out.println("TIQUETES PENDIENTES");
                    System.out.println(ticketManager.readHangingTickets());
                    System.out.println("TIQUETES TERMINADOS");
                    System.out.println(ticketManager.readFinishedTickets());
                    System.out.println("TIQUETES DISPONIBLES");
                    System.out.println(ticketManager.readAvailableTickets());
                    System.out.println("Seleccione el tiquete a consultar con su ID:");
                    try {
                        ticketID = scanner.nextInt();
                        scanner.nextLine();

                        System.out.println(ticketManager.consultTicket(ticketID));
                    } catch (NullPointerException e) {
                        System.out.println("\nERROR: El ID introducido no corresponde a uno de la lista.\n");
                    } catch (InputMismatchException e) {
                        System.out.println("\nERROR: El ID debe ser un número.\n");
                    }

                    break;
                case 4:
                    break;
                case 5:
                    break;
            }
        }
    }
    //Supporter's menu.
    public void supporterMenu(Supporter supporter) {
        System.out.println("\nSOPORTISTA\n");
        int option = -1;

        //Menu
        while (option != 6) {
            System.out.println("1: Autoasignarse un tiquete.\n" +
                    "2: Comentar tiquete.\n" +
                    "3: Terminar un tiquete.\n" +
                    "4: Visualizar los tiquetes.\n" +
                    "5: Consultar tiquete con detalle.\n" +
                    "6: Cerrar sesión.");
            option = scanner.nextInt();
            scanner.nextLine();
            switch (option) {
                case 1:
                    //The ID of the ticket is asked.
                    System.out.println("Seleccione el tiquete con su ID.");
                    System.out.println(ticketManager.readAvailableTickets());
                    int ticketID;
                    try {
                        ticketID = scanner.nextInt();
                        scanner.nextLine();
                        takeTicket(ticketID, supporter);
                        System.out.println("\n¡TIQUETE TOMADO!\n");

                    } catch (NullPointerException e) {
                        System.out.println("\nERROR: El ID introducido no existe.\n");
                    } catch (InputMismatchException e) {
                        System.out.println("\nERROR: El ID debe ser un número.\n");
                    }
                    break;
                case 2:
                    //TODO ----------------
                    System.out.println("Seleccione el tiquete con su ID.");
                    System.out.println(ticketManager.readSupporterUnfinishedTickets(supporter));
                    try {
                        ticketID = scanner.nextInt();
                        scanner.nextLine();

                        System.out.println(ticketManager.consultTicketForSupporters(ticketID, supporter));
                        System.out.println("Inserte el comentario para retroalimentación:");
                        String comment = scanner.nextLine();

                        ticketManager.addComment(ticketID, comment);
                        System.out.println("\n¡COMENTARIO AÑADIDO!\n");
                    } catch (NullPointerException e) {
                        System.out.println("\nERROR: El ID introducido no existe.\n");
                    } catch (InputMismatchException e) {
                        System.out.println("\nERROR: El ID debe ser un número.\n");
                    }

                    break;
                case 3:
                    System.out.println("Seleccione el tiquete con su ID.");
                    System.out.println(ticketManager.readSupporterUnfinishedTickets(supporter));
                    try {
                        ticketID = scanner.nextInt();
                        scanner.nextLine();
                        ticketManager.finishTicket(ticketID);
                        System.out.println("\n¡TIQUETE TERMINADO!\n");
                    } catch (NullPointerException e) {
                        System.out.println("\nERROR: El ID introducido no existe.\n");
                    } catch (InputMismatchException e) {
                        System.out.println("\nERROR: El ID debe ser un número.\n");
                    }
                    break;
                case 4:
                    System.out.println("TIQUETES PENDIENTES");
                    System.out.println(ticketManager.readSupporterUnfinishedTickets(supporter));
                    System.out.println("TIQUETES TERMINADOS");
                    System.out.println(ticketManager.readSupporterFinishedTickets(supporter));
                    System.out.println("TIQUETES DISPONIBLES");
                    System.out.println(ticketManager.readAvailableTickets());
                    break;
                case 5:
                    System.out.println("TIQUETES PENDIENTES");
                    System.out.println(ticketManager.readSupporterUnfinishedTickets(supporter));
                    System.out.println("TIQUETES TERMINADOS");
                    System.out.println(ticketManager.readSupporterFinishedTickets(supporter));
                    System.out.println("TIQUETES DISPONIBLES");
                    System.out.println(ticketManager.readAvailableTickets());
                    System.out.println("Seleccione el tiquete a consultar con su ID:");
                    try {
                        ticketID = scanner.nextInt();
                        scanner.nextLine();

                        System.out.println(ticketManager.consultTicketForSupporters(ticketID, supporter));
                    } catch (NullPointerException e) {
                        System.out.println("\nERROR: El ID introducido no corresponde a uno de la lista.\n");
                    } catch (InputMismatchException e) {
                        System.out.println("\nERROR: El ID debe ser un número.\n");
                    }
                    break;
                case 6:
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
