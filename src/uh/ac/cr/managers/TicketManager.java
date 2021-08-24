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

    public void createTicket(int ticketID, User requesterOfTicket, String problemDescription, Date date, Date deadlineExpected) {
        ticketList.insertTicketToTail(new Ticket(ticketID, requesterOfTicket, problemDescription, date, deadlineExpected));
    }
    public String readHangingTickets() {
        return ticketList.readHangingTickets();
    }
    public String readFinishedTickets() {
        return ticketList.readFinishedTickets();
    }
    public String readAvailableTickets() {
        return ticketList.readAvailableTickets();
    }
    public String readUserUnfinishedTickets(User user) {
        return ticketList.readUserUnfinishedTickets(user);
    }
    public String readUserFinishedTickets(User user) {
        return ticketList.readUserFinishedTickets(user);
    }
    public String readSupporterUnfinishedTickets(Supporter supporter) {
        return ticketList.readSupporterUnfinishedTickets(supporter);
    }
    public String readSupporterFinishedTickets(Supporter supporter) {
        return ticketList.readSupporterFinishedTickets(supporter);
    }
    public void deleteTicket(int ticketID) {
        ticketList.removeTicket(ticketList.getTicketByID(ticketID));
    }
    public String consultTicket(int ticketID) throws NullPointerException {
        Ticket ticket = ticketList.getTicketByID(ticketID);
        return ticket.getTicketDetailedInfo();
    }
    public String consultTicketForUsers(int ticketID, User user) throws NullPointerException {
        Ticket ticket = ticketList.getTicketByID(ticketID);
        if (ticket.isFromUser(user)) {
            return ticket.getTicketDetailedInfo();
        } else {
            return null;
        }
    }
    public String consultTicketForSupporters(int ticketID, Supporter supporter) throws NullPointerException {
        Ticket ticket = ticketList.getTicketByID(ticketID);
        if (ticket.wasTakenBy(supporter) || ticket.isAvailable()) {
            return ticket.getTicketDetailedInfo();
        } else {
            return null;
        }
    }

    public void addComment(int ticketID, String comment) throws NullPointerException {
        ticketList.getTicketByID(ticketID).insertComment(comment);
    }
    public void assignTicket(int ticketID, Supporter supporter) throws NullPointerException {
        ticketList.getTicketByID(ticketID).assignTicketToSupporter(supporter);
    }
    public void finishTicket(int ticketID) throws NullPointerException {
        ticketList.getTicketByID(ticketID).finishTicket();
    }

    public boolean existsID(int ticketID) {
        return ticketList.getTicketByID(ticketID) != null;
    }
}
