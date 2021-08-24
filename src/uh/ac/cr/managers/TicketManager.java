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
    public void deleteTicket(int ticketID) {
        ticketList.removeTicket(ticketList.getTicketByID(ticketID));
    }
    public void assignTicket(int ticketID, Supporter supporter) throws NullPointerException {
        ticketList.getTicketByID(ticketID).assignTicketToSupporter(supporter);
    }
    public void finishTicket(int ticketID) {
        ticketList.getTicketByID(ticketID).finishTicket();
    }

    public boolean existsID(int ticketID) {
        return ticketList.getTicketByID(ticketID) != null;
    }
}
