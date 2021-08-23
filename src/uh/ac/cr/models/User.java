package uh.ac.cr.models;

import uh.ac.cr.managers.TicketManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class User {
    protected int id;
    protected String firstName;
    protected String secondName;
    protected String department;
    protected String username;
    protected String password;

    public User() {}

    public User(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public void createTicket(int ticketID, String problemDescription, Date date, Date deadlineExpected) {
        TicketManager ticketManager = new TicketManager();
        ticketManager.createTicket(ticketID,this, problemDescription, date, deadlineExpected);
    }

    public void getMenu() {
        Scanner scanner = new Scanner(System.in);
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

                    createTicket(ticketID, problemDescription, date, deadlineExpected);
                    System.out.println("\n¡TIQUETE CREADO!\n");
                    break;
                case 2:

                    break;
                case 3:
                    break;
            }
        }


    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
