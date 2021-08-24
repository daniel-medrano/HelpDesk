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

    boolean ready;
    String input;
    int ticketID;
    int supporterID;
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
                    createTicket(user);
                    break;
                case 2:
                    visualizeTicketsOfUser(user);
                    break;
                case 3:
                    consultTicketWithDetail(user);
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
                    assignTicketToSupporter();
                    break;
                case 2:
                    visualizeTicketsOfSupervisor();
                    break;
                case 3:
                    consultTicketWithDetailSupervisor();
                    break;
                case 4:
                    printSupporterStatistics();
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
                    autoassignTicket(supporter);
                    break;
                case 2:
                    commentTicket(supporter);
                    break;
                case 3:
                    finishTicket(supporter);
                    break;
                case 4:
                    visualizeTicketsOfSupporter(supporter);
                    break;
                case 5:
                    consultTicketsWithDetailSupporter(supporter);
                    break;
                case 6:
                    break;
            }
        }
    }

    public void adminMenu() {

    }

    //USER MENU
    public void createTicket(User user) {
        ready = false;
        do {
            try {
                System.out.println("Inserte el ID del tiquete. Inserte \"Cancelar\" si no desea continuar.");
                input = scanner.next();
                scanner.nextLine();

                if (input.equals("Cancelar")) {
                    System.out.println("\nLa operación ha sido cancelada.\n");
                    //Exits the operation.
                    return;
                } else {
                    ticketID = Integer.parseInt(input);
                    if (!ticketManager.existsID(ticketID)) {
                        ready = true;
                    } else {
                        System.out.println("\nERROR: Ya existe un tiquete con este ID.\n");
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("\nERROR: El ID debe ser un número.\n");
            }
        } while (!ready);

        System.out.println("Descripción del problema. Inserte \"Cancelar\" si no desea continuar.");
        String problemDescription = scanner.nextLine();
        if (problemDescription.equals("Cancelar")) {
            System.out.println("\nLa operación ha sido cancelada.\n");
            //Exits the operation.
            return;
        }

        Date date = new Date();

        ticketManager.createTicket(ticketID, user, problemDescription, date);
        System.out.println("\n¡TIQUETE CREADO!\n");
    }

    public void visualizeTicketsOfUser(User user) {
        System.out.println("TIQUETES DE: " + user.getFullName());
        System.out.println("TIQUETES TERMINADOS");
        System.out.println(ticketManager.readUserFinishedTickets(user));
        System.out.println("TIQUETES ACTIVOS");
        System.out.println(ticketManager.readUserUnfinishedTickets(user));
    }

    public void consultTicketWithDetail(User user) {
        System.out.println("TIQUETES DE: " + user.getFullName());
        System.out.println("TIQUETES TERMINADOS");
        System.out.println(ticketManager.readUserFinishedTickets(user));
        System.out.println("TIQUETES ACTIVOS");
        System.out.println(ticketManager.readUserUnfinishedTickets(user));

        ready = false;
        do {
            try {
                System.out.println("Inserte el ID del tiquete a consultar. Inserte \"Cancelar\" si no desea continuar.");
                input = scanner.next();
                scanner.nextLine();

                if (input.equals("Cancelar")) {
                    System.out.println("\nLa operación ha sido cancelada.\n");
                    //Exits the operation.
                    return;
                } else {
                    ticketID = Integer.parseInt(input);
                    if (ticketManager.existsID(ticketID) && ticketManager.getTicket(ticketID).isFromUser(user)) {
                        System.out.println(ticketManager.consultTicketForUsers(ticketID, user));
                        ready = true;
                    } else {
                        if (ticketManager.existsID(ticketID))
                            System.out.println("\nERROR: No existe un tiquete con tal ID.\n");
                        else
                            System.out.println("\nERROR: Este tiquete no sale en la lista.\n");
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("\nERROR: El ID debe ser un número.\n");
            }
        } while (!ready);
    }


    //SUPERVISOR MENU
    public void assignTicketToSupporter() {
        System.out.println("Soportistas con sus IDs.");
        System.out.println(userManager.readSupporters());

        Supporter supporter = null;
        ready = false;
        do {
            try {
                System.out.println("Inserte el ID del soportista. Inserte \"Cancelar\" si no desea continuar.");
                input = scanner.next();
                scanner.nextLine();

                if (input.equals("Cancelar")) {
                    System.out.println("\nLa operación ha sido cancelada.\n");
                    //Exits the operation.
                    return;
                } else {
                    supporterID = Integer.parseInt(input);
                    if (userManager.existsUser(supporterID)) {
                        supporter = userManager.getSupporter(supporterID);
                        ready = true;
                    } else {
                        System.out.println("\nERROR: No existe un soportista con tal ID.\n");
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("\nERROR: El ID debe ser un número.\n");
            }
        } while (!ready);

        System.out.println("Tiquetes con sus IDs.");
        System.out.println(ticketManager.readAvailableTickets());

        ready = false;
        do {
            try {
                System.out.println("ID del tiquete. Inserte \"Cancelar\" si no desea continuar.");
                input = scanner.next();
                scanner.nextLine();

                if (input.equals("Cancelar")) {
                    System.out.println("\nLa operación ha sido cancelada.\n");
                    //Exits the operation.
                    return;
                } else {
                    ticketID = Integer.parseInt(input);
                    if (ticketManager.existsID(ticketID) && ticketManager.getTicket(ticketID).isAvailable()) {
                        ready = true;
                    } else {
                        if (!ticketManager.existsID(ticketID))
                            System.out.println("\nERROR: No existe un tiquete con tal ID.\n");
                        else
                            System.out.println("\nERROR: El ID introducido no se encuentra en la lista.\n");
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("\nERROR: El ID debe ser un número.\n");
            }
        } while (!ready);

        Date deadlineExpected = null;
        ready = false;
        do {
            System.out.println("Inserte la fecha limite para resolver el tiquete con este formato: dd/mm/yyyy. Inserte \"Cancelar\" si no desea continuar.");
            input = scanner.next();
            scanner.nextLine();
            if (input.equals("Cancelar")) {
                System.out.println("\nLa operación ha sido cancelada.\n");
                //Exist the operation.
                return;
            }
            try {
                deadlineExpected = new SimpleDateFormat("dd/MM/yyyy").parse(input);
                ready = true;
            } catch (ParseException e) {
                System.out.println("\nERROR: Formato incorrecto.\n"); }
        } while (!ready);

        ticketManager.assignTicket(ticketID, supporter, deadlineExpected);
        System.out.println("\n¡TIQUETE ASIGNADO!\n");
    }

    public void visualizeTicketsOfSupervisor() {
        System.out.println("TIQUETES PENDIENTES");
        System.out.println(ticketManager.readHangingTickets());
        System.out.println("TIQUETES TERMINADOS");
        System.out.println(ticketManager.readFinishedTickets());
        System.out.println("TIQUETES DISPONIBLES");
        System.out.println(ticketManager.readAvailableTickets());
    }

    public void consultTicketWithDetailSupervisor() {
        System.out.println("TIQUETES PENDIENTES");
        System.out.println(ticketManager.readHangingTickets());
        System.out.println("TIQUETES TERMINADOS");
        System.out.println(ticketManager.readFinishedTickets());
        System.out.println("TIQUETES DISPONIBLES");
        System.out.println(ticketManager.readAvailableTickets());
        System.out.println("Seleccione el tiquete a consultar con su ID:");

        ready = false;
        do {
            try {
                System.out.println("ID del tiquete. Inserte \"Cancelar\" si no desea continuar.");
                input = scanner.next();
                scanner.nextLine();

                if (input.equals("Cancelar")) {
                    System.out.println("\nLa operación ha sido cancelada.\n");
                    //Exits the operation.
                    return;
                } else {
                    ticketID = Integer.parseInt(input);
                    if (ticketManager.existsID(ticketID)) {
                        System.out.println(ticketManager.consultTicket(ticketID));
                        ready = true;
                    } else {
                        System.out.println("\nERROR: No existe un tiquete con tal ID.\n");
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("\nERROR: El ID debe ser un número.\n");
            }
        } while (!ready);
    }

    public void printSupporterStatistics() {
        System.out.println("ESTADÍSTICAS DE SOPORTISTAS");
        System.out.println(userManager.readSupportersWithStatistics());
    }

    //SUPPORTER MENU
    public void autoassignTicket(Supporter supporter) {
        //The ID of the ticket is asked.
        System.out.println("Tiquetes con sus IDs.");
        System.out.println(ticketManager.readAvailableTickets());
        ready = false;
        do {
            try {
                System.out.println("ID del tiquete. Inserte \"Cancelar\" si no desea continuar.");
                input = scanner.next();
                scanner.nextLine();

                if (input.equals("Cancelar")) {
                    System.out.println("\nLa operación ha sido cancelada.\n");
                    //Exits the operation.
                    return;
                } else {
                    ticketID = Integer.parseInt(input);
                    if (ticketManager.existsID(ticketID) && ticketManager.getTicket(ticketID).isAvailable()) {
                        ready = true;
                    } else {
                        if (!ticketManager.existsID(ticketID))
                            System.out.println("\nERROR: No existe un tiquete con tal ID.\n");
                        else
                            System.out.println("\nERROR: El ID introducido no se encuentra en la lista.\n");
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("\nERROR: El ID debe ser un número.\n");
            }
        } while (!ready);

        Date deadlineExpected = null;
        ready = false;
        do {
            System.out.println("Inserte la fecha limite para resolver el tiquete con este formato: dd/mm/yyyy. Inserte \"Cancelar\" si no desea continuar.");
            input = scanner.next();
            scanner.nextLine();
            if (input.equals("Cancelar")) {
                System.out.println("\nLa operación ha sido cancelada.\n");
                //Exist the operation.
                return;
            }
            try {
                deadlineExpected = new SimpleDateFormat("dd/MM/yyyy").parse(input);
                ready = true;
            } catch (ParseException e) {
                System.out.println("\nERROR: Formato incorrecto.\n"); }
        } while (!ready);

        takeTicket(ticketID, supporter, deadlineExpected);
    }

    public void commentTicket(Supporter supporter) {
        System.out.println("Tiquetes con sus IDs.");
        System.out.println(ticketManager.readSupporterUnfinishedTickets(supporter));

        ready = false;
        do {
            try {
                System.out.println("Inserte el ID del tiquete. Inserte \"Cancelar\" si no desea continuar.");
                input = scanner.next();
                scanner.nextLine();

                if (input.equals("Cancelar")) {
                    System.out.println("\nLa operación ha sido cancelada.\n");
                    //Exits the operation.
                    return;
                } else {
                    ticketID = Integer.parseInt(input);
                    if (!ticketManager.existsID(ticketID)) {
                        ready = true;
                    } else {
                        System.out.println("\nERROR: Ya existe un tiquete con este ID.\n");
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("\nERROR: El ID debe ser un número.\n");
            }
        } while (!ready);

        System.out.println("Tiquetes con sus IDs.");
        System.out.println(ticketManager.consultTicketForSupporters(ticketID, supporter));
        System.out.println("Inserte el comentario para retroalimentación. Inserte \"Cancelar\" si no desea continuar.");
        String comment = scanner.nextLine();
        if (comment.equals("Cancelar")) {
            System.out.println("\nLa operación ha sido cancelada.\n");
            //Exits the operation.
            return;
        }

        ticketManager.addComment(ticketID, comment);
        System.out.println("\n¡COMENTARIO AÑADIDO!\n");
    }

    public void finishTicket(Supporter supporter) {
        System.out.println("Tiquetes con sus IDs.");
        System.out.println(ticketManager.readSupporterUnfinishedTickets(supporter));

        ready = false;
        do {
            try {
                System.out.println("Inserte el ID del tiquete. Inserte \"Cancelar\" si no desea continuar.");
                input = scanner.next();
                scanner.nextLine();

                if (input.equals("Cancelar")) {
                    System.out.println("\nLa operación ha sido cancelada.\n");
                    //Exits the operation.
                    return;
                } else {
                    ticketID = Integer.parseInt(input);
                    if (ticketManager.existsID(ticketID) && !ticketManager.getTicket(ticketID).isFinished() && ticketManager.getTicket(ticketID).wasTakenBy(supporter)) {
                        ready = true;
                    } else {
                        if (!ticketManager.existsID(ticketID))
                            System.out.println("\nERROR: No existe un tiquete con este ID.\n");
                        else if (ticketManager.getTicket(ticketID).isFinished() || !ticketManager.getTicket(ticketID).wasTakenBy(supporter)) {
                            System.out.println("\nERROR: Este tiquete no se encuentra en la lista.\n");
                        }
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("\nERROR: El ID debe ser un número.\n");
            }
        } while (!ready);


        ticketManager.finishTicket(ticketID);
        System.out.println("\n¡TIQUETE TERMINADO!\n");

    }

    public void visualizeTicketsOfSupporter(Supporter supporter) {
        System.out.println("TIQUETES PENDIENTES");
        System.out.println(ticketManager.readSupporterUnfinishedTickets(supporter));
        System.out.println("TIQUETES TERMINADOS");
        System.out.println(ticketManager.readSupporterFinishedTickets(supporter));
        System.out.println("TIQUETES DISPONIBLES");
        System.out.println(ticketManager.readAvailableTickets());
    }

    public void consultTicketsWithDetailSupporter(Supporter supporter) {
        System.out.println("TIQUETES PENDIENTES");
        System.out.println(ticketManager.readSupporterUnfinishedTickets(supporter));
        System.out.println("TIQUETES TERMINADOS");
        System.out.println(ticketManager.readSupporterFinishedTickets(supporter));
        System.out.println("TIQUETES DISPONIBLES");
        System.out.println(ticketManager.readAvailableTickets());

        ready = false;
        do {
            try {
                System.out.println("Inserte el ID del tiquete. Inserte \"Cancelar\" si no desea continuar.");
                input = scanner.next();
                scanner.nextLine();

                if (input.equals("Cancelar")) {
                    System.out.println("\nLa operación ha sido cancelada.\n");
                    //Exits the operation.
                    return;
                } else {
                    ticketID = Integer.parseInt(input);
                    if (ticketManager.existsID(ticketID) && (ticketManager.getTicket(ticketID).wasTakenBy(supporter) || ticketManager.getTicket(ticketID).isAvailable())) {
                        System.out.println(ticketManager.consultTicketForSupporters(ticketID, supporter));
                        ready = true;
                    } else {
                        System.out.println("\nERROR: No existe un tiquete con tal ID.\n");
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("\nERROR: El ID debe ser un número.\n");
            } catch (NullPointerException e) {
                System.out.println();
            }
        } while (!ready);
    }

    public void takeTicket(int ticketID, Supporter supporter, Date deadlineExpected) throws NullPointerException {
        ticketManager.assignTicket(ticketID, supporter, deadlineExpected);
    }
}
