package uh.ac.cr.models;

import uh.ac.cr.managers.TicketManager;

public class Supporter extends User {

    private int ticketsFinished;
    private int ticketsFinishedOnTime;
    private int ticketsFinishedOverdue;

    public Supporter(int id, String firstName, String secondName, String department, String username, String password) {
        super(id, firstName, secondName, department, username, password);
        this.ticketsFinished = 0;
        this.ticketsFinishedOnTime = 0;
        this.ticketsFinishedOverdue = 0;
    }
    public String getSupporterInfo() {
        return id + " - " + username + ". " + getFullName() + "." + "\n" +
                getSpacing() + "Tiquetes finalizados: " + ticketsFinished + ".\n" +
                getSpacing() + "Tiquetes finalizados a tiempo: " + ticketsFinishedOnTime + ".\n" +
                getSpacing() + "Tiquetes finalizados tarde: " + ticketsFinishedOverdue + ".";
    }

    public int getTicketsFinished() {
        return ticketsFinished;
    }

    public void increaseTicketsFinished() {
        ticketsFinished++;
    }

    public void increaseTicketsFinishedOnTime() {
        ticketsFinishedOnTime++;
    }

    public void increaseTicketsFinishedOverdue() {
        ticketsFinishedOverdue++;
    }

}
