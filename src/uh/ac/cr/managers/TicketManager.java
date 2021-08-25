package uh.ac.cr.managers;

import uh.ac.cr.dataStructures.TicketList;
import uh.ac.cr.models.Supporter;
import uh.ac.cr.models.Ticket;
import uh.ac.cr.models.User;

import java.util.Date;

public class TicketManager {
    //Linked list for the tickets.
    TicketList ticketList;
    //Constructor where the linked list is initialized.
    public TicketManager() {
        this.ticketList = new TicketList();
    }
    //Method to create a new ticket and insert it at the end of the list.
    public void createTicket(int ticketID, User requesterOfTicket, String problemDescription, Date date) {
        ticketList.insertTicketToTail(new Ticket(ticketID, requesterOfTicket, problemDescription, date));
    }
    //Method that returns a String of the tickets that are not either finished or available.
    public String readHangingTickets() {
        return ticketList.readHangingTickets();
    }
    //Method that returns a String of all the tickets that are finished.
    public String readFinishedTickets() {
        return ticketList.readFinishedTickets();
    }
    //Method that returns a String of all the tickets that are available or not taken by anyone.
    public String readAvailableTickets() {
        return ticketList.readAvailableTickets();
    }
    //Method that returns a String of all the tickets that are not finished and that belong to a specific user.
    public String readUserUnfinishedTickets(User user) {
        return ticketList.readUserUnfinishedTickets(user);
    }
    //Method that returns a String of all the tickets that are finished and that belong to a specific user.
    public String readUserFinishedTickets(User user) {
        return ticketList.readUserFinishedTickets(user);
    }
    //Method that returns a String of all the tickets that are not finished and that belong to a specific supporter.
    public String readSupporterUnfinishedTickets(Supporter supporter) {
        return ticketList.readSupporterUnfinishedTickets(supporter);
    }
    //Method that returns a String of all the tickets that are finished and that belong to a specific supporter.
    public String readSupporterFinishedTickets(Supporter supporter) {
        return ticketList.readSupporterFinishedTickets(supporter);
    }
    //Method that returns a String of a ticket, which is detailed information about this ticket.
    public String consultTicket(int ticketID) throws NullPointerException {
        Ticket ticket = ticketList.getTicketByID(ticketID);
        return ticket.getTicketDetailedInfo();
    }
    //Method that returns a String of a ticket, which is detailed information about this ticket.
    public String consultTicketForUsers(int ticketID, User user) throws NullPointerException {
        Ticket ticket = ticketList.getTicketByID(ticketID);
        if (ticket.isFromUser(user)) {
            return ticket.getTicketDetailedInfo();
        } else {
            return null;
        }
    }
    //Method that returns a String of a ticket, which is detailed information about this ticket.
    public String consultTicketForSupporters(int ticketID, Supporter supporter) throws NullPointerException {
        Ticket ticket = ticketList.getTicketByID(ticketID);
        if (ticket.wasTakenBy(supporter) || ticket.isAvailable()) {
            return ticket.getTicketDetailedInfo();
        } else {
            return null;
        }
    }
    //Method that allows the supporter to add comments to a specific ticket.
    public void addComment(int ticketID, String comment) throws NullPointerException {
        ticketList.getTicketByID(ticketID).insertComment(comment);
    }
    //Method to assign the ticket to a supporter, so that the ticket gets solved.
    public void assignTicket(int ticketID, Supporter supporter, Date deadlineExpected) throws NullPointerException {
        Ticket ticket = ticketList.getTicketByID(ticketID);
        ticket.assignTicketToSupporter(supporter);
        ticket.setDeadlineExpected(deadlineExpected);
    }
    //Method to indicate that the ticket is finished.
    public void finishTicket(int ticketID) throws NullPointerException {
        ticketList.getTicketByID(ticketID).finishTicket();
    }
    //Method to verify if a ticket ID exists.
    public boolean existsID(int ticketID) {
        return ticketList.getTicketByID(ticketID) != null;
    }
    //Method that returns a ticket by its ID.
    public Ticket getTicket(int ticketID) {
        return ticketList.getTicketByID(ticketID);
    }
}
